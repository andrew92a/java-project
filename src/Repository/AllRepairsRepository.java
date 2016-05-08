package Repository;

import Models.Orders.Repair;

import java.util.List;

/**
 * Created by pawel on 5/3/16.
 */
public class AllRepairsRepository extends BaseRepository
{
    public List<Repair> all()
    {
        return Repair.findAll();
    }

    public List<Repair> search(String search)
    {
        search = '%' + search + '%';
        return Repair.where("Id LIKE ?", search).orderBy("Id asc");
    }

    public List<Repair> activeRepair()
    {
        return Repair.where("Status = 1").orderBy("Id asc");

    }
}