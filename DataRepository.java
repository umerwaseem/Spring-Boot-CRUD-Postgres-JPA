package com.example.demo.repository;

 import com.example.demo.model.BioData;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.query.Param;
 import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DataRepository extends JpaRepository<BioData, Long> {
//    @Query("SELECT u FROM BioData u WHERE u.name="+hold.getName())
//    List<BioData> findUser(BioData holData);

//    List<Data> findUserByNameAndPhone(String name, String phone);

//    @Query()
//    public Data getNaem()


    @Query("select bd from BioData bd where bd.name=:#{#bio.name} and bd.phone=:#{#bio.phone}")
    List<BioData> findUser(@Param("bio") BioData employee);
}
