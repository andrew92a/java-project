package Repository;

import Models.Orders.Client;

import java.util.List;

/**
 * Created by pawel on 4/29/16.
 */
public class ClientsRepository extends BaseRepository {

    public List<Client> all() {
        return Client.findAll();
    }

    public List<Client> search(String search) {
        search = '%' + search + '%';
        return Client.where("name LIKE ?", search).orderBy("name asc");
    }
}
