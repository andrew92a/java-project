package Models;

import org.javalite.activejdbc.Model;

/**
 * Created by pawel on 4/29/16.
 */
public class NewClient extends Model {
    String FName;
    String SName;
    String Phone1;

    public NewClient(String fn,String sn,String ph1)
    {
        FName = fn;
        SName = sn;
        Phone1 = ph1;
    }

    public void RegisterClient()
    {
        NewClient c = new NewClient();
        c.set("sFname",FName);
        c.set("sSname",SName);
        c.set("sPhone",Phone1);
    }
}
