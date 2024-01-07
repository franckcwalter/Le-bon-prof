<?php
class User
{
    // Connection
    private $conn;
    // Table
    private $db_table = "user";
    // Columns
    public $id;
    public $email;
    public $password;
    public $first_name;
    public $idRole;
    // Db connection
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // GET ALL
    public function getUsers()
    {
        $sqlQuery = "SELECT id, email, first_name, idRole FROM " . $this->db_table . "";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->execute();
        return $stmt;
    }

    // CREATE
    public function createUser()
    {
        $sqlQuery = "INSERT INTO
                        " . $this->db_table . "
                    SET
                        email = :email, 
                        password = :password, 
                        first_name = :first_name, 
                        idRole = :idRole";

        $stmt = $this->conn->prepare($sqlQuery);

        // sanitize
        $this->email = htmlspecialchars(strip_tags($this->email));
        $this->password = htmlspecialchars(strip_tags($this->password));
        $this->first_name = htmlspecialchars(strip_tags($this->first_name));
        $this->idRole = htmlspecialchars(strip_tags($this->idRole));

        // bind data
        $stmt->bindParam(":email", $this->email);
        $stmt->bindParam(":password", $this->password);
        $stmt->bindParam(":first_name", $this->first_name);
        $stmt->bindParam(":idRole", $this->idRole);

        try {
            if ($stmt->execute()) {
                $lastInsertedId = $this->conn->lastInsertId();
                return $lastInsertedId;
            }
        } catch (PDOException $e) {

            if ($e->getCode() == '23000') {
                return -1;
            } else {
                // Gérez d'autres types d'erreurs 
                return false;
            }
        }

        return false;

        /*
        if ($stmt->execute()) {

            $lastInsertedId = $this->conn->lastInsertId();

            return $lastInsertedId;

            //  return true;
        }
        return false;
        */
    }

    // READ single
    public function getUserFromId()
    {
        $sqlQuery = "SELECT
                        id, 
                        email, 
                        first_name, 
                        idRole
                      FROM
                        " . $this->db_table . "
                    WHERE 
                       id = ?
                    LIMIT 0,1";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(1, $this->id);
        $stmt->execute();
        $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->email = $dataRow['email'];
        $this->first_name = $dataRow['first_name'];
        $this->idRole = $dataRow['idRole'];
    }


    // LOG IN 
    public function getUserFromEmail()
    {

        $sqlQuery = "SELECT
                    id, 
                    email, 
                    password,
                    first_name, 
                    idRole
                    FROM
                    " . $this->db_table . "
                    WHERE 
                    email = ?
                    LIMIT 0,1";

        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(1, $this->email);
        $stmt->execute();
        $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($dataRow == false) {
            return null;
        } else {
            $this->id = $dataRow['id'];
            $this->email = $dataRow['email'];
            $this->password = $dataRow['password'];
            $this->first_name = $dataRow['first_name'];
            $this->idRole = $dataRow['idRole'];
        }
    }

    // UPDATE
    public function updateUser()
    {
        $sqlQuery = "UPDATE
                        " . $this->db_table . "
                    SET
                        email = :email, 
                        password = :password, 
                        first_name = :first_name, 
                        idRole = :idRole
                    WHERE 
                        id = :id";

        $stmt = $this->conn->prepare($sqlQuery);

        $this->id = htmlspecialchars(strip_tags($this->id));
        $this->email = htmlspecialchars(strip_tags($this->email));
        $this->password = htmlspecialchars(strip_tags($this->password));
        $this->first_name = htmlspecialchars(strip_tags($this->first_name));
        $this->idRole = htmlspecialchars(strip_tags($this->idRole));

        // bind data
        $stmt->bindParam(":id", $this->id);
        $stmt->bindParam(":email", $this->email);
        $stmt->bindParam(":password", $this->password);
        $stmt->bindParam(":first_name", $this->first_name);
        $stmt->bindParam(":idRole", $this->idRole);

        if ($stmt->execute()) {
            return true;
        }
        return false;
    }

    // DELETE
    function deleteUser()
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
}
