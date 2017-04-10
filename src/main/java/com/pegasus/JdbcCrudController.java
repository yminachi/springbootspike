package com.pegasus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/jdbc")
public class JdbcCrudController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(path = "/pegasus", method = RequestMethod.PUT)
    public String save(@RequestBody Pegasus pegasus) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("insert into pegasus (name, title) value (?, ?)", new String[] {"id"});
                    ps.setString(1, pegasus.name);
                    ps.setString(2, pegasus.title);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().toString();
    }

    @RequestMapping(path = "/pegasus/{id}/**", method = RequestMethod.GET)
    public String get(@PathVariable String id) {
        List<Pegasus> pegasi = jdbcTemplate.query("select id, name, title from pegasus where id = ?", new Object[]{id}, new RowMapper<Pegasus>() {

            @Override
            public Pegasus mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Pegasus(rs.getString("name"), rs.getString("title"));
            }
        });
        return pegasi.get(0).toString();
    }
}