package ff20.demo.repository;

import ff20.demo.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminDbRepository implements AdminRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    SqlRowSet sqlRowSet;


    public Admin read(String email) {

        String sql = "SELECT * FROM admin WHERE email ='" + email + "'";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            return new Admin(sqlRowSet.getString("email"), sqlRowSet.getString("password"));
        }

        return null;
    }

    @Override
    public <S extends Admin> S save(S s) {
        return null;
    }

    @Override
    public <S extends Admin> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Admin> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Admin> findAll() {

        List<Admin> admins = new ArrayList<>();

        String sql = "SELECT * FROM admin";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            admins.add(new Admin(sqlRowSet.getString("email"), sqlRowSet.getString("password")));

        }

        return admins;
    }

    @Override
    public Iterable<Admin> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Admin admin) {

    }

    @Override
    public void deleteAll(Iterable<? extends Admin> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
