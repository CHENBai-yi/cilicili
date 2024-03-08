package site.cilicili.common.logger;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

/**
 * @author BaiYiChen
 */
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
                }
        )
})
public class ExplainInterceptor implements InnerInterceptor {

    /**
     * @param executor      Executor(可能是代理对象)
     * @param ms            MappedStatement
     * @param parameter     parameter
     * @param rowBounds     rowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     * @throws SQLException
     */
    @Override
    public void beforeQuery(final Executor executor, final MappedStatement ms, final Object parameter, final RowBounds rowBounds, final ResultHandler resultHandler, final BoundSql boundSql) throws SQLException {
        if (ms.getSqlCommandType() == SqlCommandType.SELECT) {
            Configuration configuration = ms.getConfiguration();
            Connection connection = executor.getTransaction().getConnection();
            sqlExplain(configuration, ms, boundSql, connection, parameter);
        }
        InnerInterceptor.super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }

    /**
     * @param executor  Executor(可能是代理对象)
     * @param ms        MappedStatement
     * @param parameter parameter
     * @throws SQLException
     */
    @Override
    public void beforeUpdate(final Executor executor, final MappedStatement ms, final Object parameter) throws SQLException {
        if (ms.getSqlCommandType() == SqlCommandType.UPDATE) {
            Configuration configuration = ms.getConfiguration();
            Connection connection = executor.getTransaction().getConnection();
            BoundSql boundSql = ms.getBoundSql(parameter);
            sqlExplain(configuration, ms, boundSql, connection, parameter);
        }
        InnerInterceptor.super.beforeUpdate(executor, ms, parameter);
    }

    private void sqlExplain(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Connection connection, Object parameter) {
        // 这里注意：EXPLAIN后面必须要有空格，否则sql为： explainselect报错
        StringBuilder explain = new StringBuilder("EXPLAIN ");
        String sqlExplain = explain.append(boundSql.getSql()).toString();
        System.out.println("============================================");
        System.out.println(sqlExplain);
        System.out.println("============================================");
        StaticSqlSource sqlSource = new StaticSqlSource(configuration, sqlExplain, boundSql.getParameterMappings());
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, "explain_sql", sqlSource, SqlCommandType.SELECT);
        MappedStatement queryStatement = builder.build();
        builder.resultMaps(mappedStatement.getResultMaps()).resultSetType(mappedStatement.getResultSetType())
                .statementType(mappedStatement.getStatementType());
        DefaultParameterHandler handler = new DefaultParameterHandler(queryStatement, parameter, boundSql);
        try {
            PreparedStatement stmt = connection.prepareStatement(sqlExplain);
            handler.setParameters(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int index = Optional.ofNullable(rs.getString("Extra"))
                        .map(extra -> extra.indexOf("Using index")).orElse(0);
                // 判断，是否走了索引。还是走的Using where
                if (index == -1) {
                    log.error("Error:Full table operator is prohibited. SQL: {}", boundSql.getSql());
                }
                // 判断，是否走到索引idx_ProName上
                if (!"idx_ProName".equals(rs.getString("key"))) {
                    log.error("Error:idx is not used. SQL: {}", boundSql.getSql());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setProperties(Properties properties) {
        InnerInterceptor.super.setProperties(properties);
    }
}
