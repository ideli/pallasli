package physics.app.dao;

import com.lyt.pallas.jdbc.model.Entity;

public interface BaseDAO {
	public abstract <E extends Entity> E save(Entity entity);

	public abstract Boolean delete(Entity entity);

	public abstract <E extends Entity> E update(Entity entity);

	public abstract <E extends Entity> E getById(String id);
}
