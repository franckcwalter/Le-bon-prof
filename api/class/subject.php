<?php
class Subject
{
    // Connection
    private $conn;

    // Table
    private $db_table = "subject";

    // Columns
    public $id;
    public $code;
    public $name;

    // Db connection
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // GET ALL
    public function getSubjects()
    {
        $sqlQuery = "SELECT id, code, name FROM " . $this->db_table . "";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->execute();
        return $stmt;
    }

    /* NOT CHECKED 
    // CREATE
    public function createSubjects()
    {
        $sqlQuery = "INSERT INTO
                        " . $this->db_table . "
                    SET
                    code = :code, 
                    name = :name";


        $stmt = $this->conn->prepare($sqlQuery);

        // sanitize
        $this->code = htmlspecialchars(strip_tags($this->code));
        $this->name = htmlspecialchars(strip_tags($this->name));

        // bind data
        $stmt->bindParam(":code", $this->code);
        $stmt->bindParam(":name", $this->name);

        if ($stmt->execute()) {
            return true;
        }
        return false;
    }

    // READ single
    public function getSubject()
    {
        $sqlQuery = "SELECT
                        id, 
                        code,
                        name
                      FROM
                        " . $this->db_table . "
                    WHERE 
                       id = ?
                    LIMIT 0,1";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(1, $this->id);
        $stmt->execute();
        $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->code = $dataRow['code'];
        $this->name = $dataRow['name'];
    }

    // UPDATE
    public function updateSubject()
    {
        $sqlQuery = "UPDATE
                        " . $this->db_table . "
                    SET
                        code = :code, 
                        name = :name, 
                    WHERE 
                        id = :id";

        $stmt = $this->conn->prepare($sqlQuery);

        $this->id = htmlspecialchars(strip_tags($this->id));
        $this->code = htmlspecialchars(strip_tags($this->code));
        $this->name = htmlspecialchars(strip_tags($this->name));

        // bind data
        $stmt->bindParam(":id", $this->id);
        $stmt->bindParam(":code", $this->code);
        $stmt->bindParam(":name", $this->name);

        if ($stmt->execute()) {
            return true;
        }
        return false;
    }

    // DELETE
    function deleteAd()
    {
        $sqlQuery = "DELETE FROM " . $this->db_table . " WHERE id = ?";
        $stmt = $this->conn->prepare($sqlQuery);

        $this->id = htmlspecialchars(strip_tags($this->id));

        $stmt->bindParam(1, $this->id);

        if ($stmt->execute()) {
            return true;
        }
        return false;
    }

    */
}
