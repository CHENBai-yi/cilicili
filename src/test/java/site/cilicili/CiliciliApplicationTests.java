package site.cilicili;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.cilicili.frontend.common.pojo.SearchRedisHelper;

@SpringBootTest
class CiliciliApplicationTests {

    public static void main(String[] args) {
        // $2a$10$CNKI5n6pyA2KoBuDg4k5recg2a5v22eqZigksjLY1.CQ8wxQOEL/S
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
    }

    @Autowired
    private SearchRedisHelper searchRedisHelper;

    @Test
    void contextLoads() {
        searchRedisHelper.preDestroy();
    }
}
