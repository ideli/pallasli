package physics.app.dao.imp;

import physics.app.dao.BaseDAO;

import com.lyt.pallas.jdbc.model.Entity;

public abstract class AbstractBaseDAO<T extends Entity> implements BaseDAO {
	public abstract <E extends Entity> E save(Entity entity);

	public abstract Boolean delete(Entity entity);

	public abstract <E extends Entity> E update(Entity entity);

	public abstract <E extends Entity> E getById(String id);
}
