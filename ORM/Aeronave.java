
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "aeronave")
public class Aeronave {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String modelo;

    public Aeronave() {
    }

    public Aeronave(String modelo) {
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Aeronave [id=" + id + ", modelo=" + modelo + "]";
    }
}

