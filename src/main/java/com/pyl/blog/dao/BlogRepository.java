package com.pyl.blog.dao;

import com.pyl.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blog b where b.title like ?1 or b.content LIKE ?1 ")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views+1")
    int updateViews(Long id);


    @Query(value ="select date_format(b.update_time, '%Y') as year from t_blog b group by date_format(b.update_time, '%Y') order by year DESC", nativeQuery = true)
    List<String> findGroupYear();

    @Query(value = "select * from t_blog b where date_format(b.update_time,'%Y') = ?1",nativeQuery = true)
    List<Blog> findByYear(String year);

}
