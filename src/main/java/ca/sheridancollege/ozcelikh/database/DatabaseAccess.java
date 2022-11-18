package ca.sheridancollege.ozcelikh.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ozcelikh.beans.Project;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<Project> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM project ORDER BY vote";

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Project>(Project.class));
	}


	public Project findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM project WHERE id = :id";
		namedParameters.addValue("id", id);

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Project>(Project.class)).get(0);
	}

//	public void deleteById(Long id) {
//
//		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
//
//		String query = "DELETE FROM project WHERE id = :id";
//		namedParameters.addValue("id", id);
//
//		jdbc.update(query, namedParameters);
//
//	}

	public Long save(Project p) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

		String query = "INSERT INTO project(title, teamName, link) " + "VALUES(:title, :teamName, :link)";

		namedParameters.addValue("title", p.getTitle());
		namedParameters.addValue("teamName", p.getTeamName());
		namedParameters.addValue("link", p.getLink());
//		namedParameters.addValue("vote", p.getVote());
		

		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}


	public Long count() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT count(*) FROM project";

		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}

	public int updateProject(Project p) {

		String query = "UPDATE project SET vote=:vote WHERE id=:id;";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		namedParameters.addValue("id", p.getId());
		namedParameters.addValue("vote", p.getVote());
		

		return jdbc.update(query, namedParameters);
	}

}
