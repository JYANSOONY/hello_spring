package hello.hellospring.repository;

import java.util.Optional;

import javax.sql.DataSource;

import hello.hellospring.domain.Member;

import java.util.List;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Member save(Member member){
        return null;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}