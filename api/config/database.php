
<?php
class Database
{
    private $host = "mysql-fwadevidfinalproject.alwaysdata.net";
    private $database_name = "fwadevidfinalproject_db";
    private $username = "341761";
    private $password = "g*vwrF!A9fqngzR";
    public $conn;
    public function getConnection()
    {
        $this->conn = null;
        try {
            $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->database_name, $this->username, $this->password);
            $this->conn->exec("set names utf8");
        } catch (PDOException $exception) {
            echo "Database could not be connected: " . $exception->getMessage();
        }
        return $this->conn;
    }
}
?>
