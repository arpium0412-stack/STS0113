package com.korea.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends 
JpaRepository<Member,Long>,
QuerydslPredicateExecutor<Member>{

List<Member> findAllByOrderByIdxDesc();
List<Member> findAllByNameContainingOrderByIdxDesc(String name);
List<Member> findAllBySample6AddressContainingOrderByIdxDesc(String address);

Boolean existsByUsername(String username);

Member findByUsername(String username);

@Query(value = "select max(seq) + 1 from Member"
	   ,nativeQuery = true)
Long findMaxSeq();

}