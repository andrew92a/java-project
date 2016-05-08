package Models.Orders;

import org.javalite.activejdbc.Model;

public class Client extends Model {

    // gdy korzystamy ze sterownika Active JDBC nie ma sensu dodawac atrybutow ktore sa kolumnami w bazie
    // do ustawienia atrybutow sluzy this.set("kolumna", "wartosc")
    // do pobierania: this.get("kolumna")
    // w tej klasie mozna dodawac methody ktore sa bezposrednio zwiazane z tym modelem, ustawiac relacje z innymi modelami itp.

    public Client()
    {

    }
}

