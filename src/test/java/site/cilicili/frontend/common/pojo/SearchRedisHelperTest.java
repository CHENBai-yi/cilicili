package site.cilicili.frontend.common.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.frontend.course.domain.dto.GetCourseListResponse;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.mapper.CoursesMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchRedisHelperTest {

    @Mock
    private RedisTemplate mockRedisTemplate;
    @Mock
    private StringRedisTemplate mockStringRedisTemplate;
    @Mock
    private CoursesMapper mockCoursesMapper;
    @Mock
    private DbChangeConfig mockDbChangeConf;

    private SearchRedisHelper searchRedisHelperUnderTest;

    @BeforeEach
    void setUp() {
        searchRedisHelperUnderTest = new SearchRedisHelper(mockRedisTemplate, mockStringRedisTemplate,
                mockCoursesMapper, mockDbChangeConf);
    }

    @Test
    void testSetHotSearchExpireTime() {
        // Setup
        // Run the test
        searchRedisHelperUnderTest.setHotSearchExpireTime();

        // Verify the results
        verify(mockRedisTemplate).expire("course_hot_search", 0L, TimeUnit.SECONDS);
    }

    @Test
    void testAddRedisRecentSearch() {
        // Setup
        when(mockRedisTemplate.opsForZSet()).thenReturn(null);

        // Run the test
        searchRedisHelperUnderTest.addRedisRecentSearch("query", 0L);

        // Verify the results
    }

    @Test
    void testListHotSearch() {
        // Setup
        final Set<HotSearch> expectedResult = Set.of(HotSearch.builder()
                .score(0.0)
                .courseListResponse(new GetCourseListResponse())
                .build());
        when(mockRedisTemplate.opsForZSet()).thenReturn(null);

        // Run the test
        final Set<HotSearch> result = searchRedisHelperUnderTest.listHotSearch();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAddRedisHotSearch() {
        // Setup
        final GetCourseListResponse getCourseListResponse = new GetCourseListResponse();
        getCourseListResponse.setView("view");
        getCourseListResponse.setDm(0L);
        getCourseListResponse.setId(0);
        getCourseListResponse.setTitle("title");
        getCourseListResponse.setFaver("faver");
        final List<GetCourseListResponse> productList = List.of(getCourseListResponse);
        when(mockRedisTemplate.opsForZSet()).thenReturn(null);

        // Run the test
        searchRedisHelperUnderTest.addRedisHotSearch(productList);

        // Verify the results
    }

    @Test
    void testListRecentSearch() {
        // Setup
        final UserRecentSearch userRecentSearch = new UserRecentSearch();
        userRecentSearch.setSearchInfo("query");
        userRecentSearch.setUnionId(0L);
        final Set<UserRecentSearch> expectedResult = Set.of(userRecentSearch);
        when(mockRedisTemplate.opsForZSet()).thenReturn(null);

        // Run the test
        final Set<UserRecentSearch> result = searchRedisHelperUnderTest.listRecentSearch(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetVisitCountIncr() {
        // Setup
        when(mockStringRedisTemplate.opsForValue()).thenReturn(null);

        // Configure CoursesMapper.queryById(...).
        final CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        coursesEntity.setName("name");
        coursesEntity.setTeacher("teacher");
        coursesEntity.setCourseId(0);
        coursesEntity.setVis(0L);
        when(mockCoursesMapper.queryById(0)).thenReturn(coursesEntity);

        // Run the test
        searchRedisHelperUnderTest.setVisitCountIncr(0L);

        // Verify the results
    }

    @Test
    void testGetVisitCountIncr() {
        // Setup
        when(mockStringRedisTemplate.opsForValue()).thenReturn(null);

        // Run the test
        final Long result = searchRedisHelperUnderTest.getVisitCountIncr(0L);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testPreDestroy() {
        // Setup
        when(mockStringRedisTemplate.keys("pattern")).thenReturn(Set.of("value"));
        when(mockStringRedisTemplate.opsForValue()).thenReturn(null);
        when(mockDbChangeConf.getFrontend()).thenReturn("result");

        // Configure CoursesMapper.updateBatch(...).
        final CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        coursesEntity.setName("name");
        coursesEntity.setTeacher("teacher");
        coursesEntity.setCourseId(0);
        coursesEntity.setVis(0L);
        final List<CoursesEntity> coursesEntities = List.of(coursesEntity);
        when(mockCoursesMapper.updateBatch(coursesEntities)).thenReturn(0);

        // Run the test
        searchRedisHelperUnderTest.preDestroy();

        // Verify the results
        verify(mockStringRedisTemplate).delete("key");
    }

    @Test
    void testPreDestroy_StringRedisTemplateKeysReturnsNull() {
        // Setup
        when(mockStringRedisTemplate.keys("pattern")).thenReturn(null);

        // Run the test
        searchRedisHelperUnderTest.preDestroy();

        // Verify the results
    }

    @Test
    void testPreDestroy_StringRedisTemplateKeysReturnsNoItems() {
        // Setup
        when(mockStringRedisTemplate.keys("pattern")).thenReturn(Collections.emptySet());
        when(mockStringRedisTemplate.opsForValue()).thenReturn(null);
        when(mockDbChangeConf.getFrontend()).thenReturn("result");

        // Configure CoursesMapper.updateBatch(...).
        final CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setCreatedAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        coursesEntity.setName("name");
        coursesEntity.setTeacher("teacher");
        coursesEntity.setCourseId(0);
        coursesEntity.setVis(0L);
        final List<CoursesEntity> coursesEntities = List.of(coursesEntity);
        when(mockCoursesMapper.updateBatch(coursesEntities)).thenReturn(0);

        // Run the test
        searchRedisHelperUnderTest.preDestroy();

        // Verify the results
        verify(mockStringRedisTemplate).delete("key");
    }
}
