<?php
class Ad
{
    // Connection
    private $conn;

    // Table
    private $db_table = "ad";

    // Columns
    public $id;
    public $ad_reference;
    public $title;
    public $photo;
    public $description;
    public $place;
    public $location;
    public $price;
    public $created_at;
    public $approved;
    public $idUser;
    public $first_name;

    // Db connection
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // GET ALL
    public function getAds()
    {
        $sqlQuery = "SELECT ad.id, ad_reference, title, photo, description, place, location, price, created_at, approved, idUser, user.first_name FROM " . $this->db_table . " ";
        $sqlQuery .= "INNER JOIN user ON ad.idUser = user.id ";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->execute();
        return $stmt;
    }

    // CREATE
    public function createAd()
    {
        $sqlQuery = "INSERT INTO
                        " . $this->db_table . "
                    SET
                    ad_reference = :ad_reference, 
                    title = :title, 
                    photo = :photo, 
                    description = :description, 
                    place = :place, 
                    location = :location, 
                    price = :price, 
                    created_at = :created_at, 
                    approved = :approved, 
                    idUser = :idUser";


        $stmt = $this->conn->prepare($sqlQuery);

        // sanitize
        $this->ad_reference = htmlspecialchars(strip_tags($this->ad_reference));
        $this->title = htmlspecialchars(strip_tags($this->title));
        $this->photo = htmlspecialchars(strip_tags($this->photo));
        $this->description = htmlspecialchars(strip_tags($this->description));
        $this->place = htmlspecialchars(strip_tags($this->place));
        $this->location = htmlspecialchars(strip_tags($this->location));
        $this->price = htmlspecialchars(strip_tags($this->price));
        $this->created_at = htmlspecialchars(strip_tags($this->created_at));
        $this->approved = htmlspecialchars(strip_tags($this->approved));
        $this->idUser = htmlspecialchars(strip_tags($this->idUser));

        // bind data
        $stmt->bindParam(":ad_reference", $this->ad_reference);
        $stmt->bindParam(":title", $this->title);
        $stmt->bindParam(":photo", $this->photo);
        $stmt->bindParam(":description", $this->description);
        $stmt->bindParam(":place", $this->place);
        $stmt->bindParam(":location", $this->location);
        $stmt->bindParam(":price", $this->price);
        $stmt->bindParam(":created_at", $this->created_at);
        $stmt->bindParam(":approved", $this->approved);
        $stmt->bindParam(":idUser", $this->idUser);

        if ($stmt->execute()) {

            $lastInsertedId = $this->conn->lastInsertId();
            return $lastInsertedId;
        }
        return false;
    }

    // READ single
    public function getAd()
    {
        $sqlQuery = "SELECT
                        ad.id, 
                        ad_reference,
                        title, 
                        photo,
                        description,
                        place,
                        location,
                        price,
                        created_at,
                        approved,
                        idUser,
                        user.first_name
                      FROM
                        " . $this->db_table . "
                    INNER JOIN user ON ad.idUser = user.id 
                    WHERE 
                       ad.id = ?
                    LIMIT 0,1";
        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(1, $this->id);
        $stmt->execute();
        $dataRow = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->ad_reference = $dataRow['ad_reference'];
        $this->title = $dataRow['title'];
        $this->photo = $dataRow['photo'];
        $this->description = $dataRow['description'];
        $this->place = $dataRow['place'];
        $this->location = $dataRow['location'];
        $this->price = $dataRow['price'];
        $this->created_at = $dataRow['created_at'];
        $this->approved = $dataRow['approved'];
        $this->idUser = $dataRow['idUser'];
        $this->first_name = $dataRow['first_name'];
    }

    // UPDATE
    public function updateAd()
    {
        $sqlQuery = "UPDATE
                        " . $this->db_table . "
                    SET
                        ad_reference = :ad_reference, 
                        title = :title, 
                        photo = :photo, 
                        description = :description, 
                        place = :place, 
                        location = :location, 
                        price = :price, 
                        created_at = :created_at, 
                        approved = :approved, 
                        idUser = :idUser
                    WHERE 
                        id = :id";

        $stmt = $this->conn->prepare($sqlQuery);

        $this->id = htmlspecialchars(strip_tags($this->id));
        $this->title = htmlspecialchars(strip_tags($this->title));
        $this->photo = htmlspecialchars(strip_tags($this->photo));
        $this->description = htmlspecialchars(strip_tags($this->description));
        $this->place = htmlspecialchars(strip_tags($this->place));
        $this->location = htmlspecialchars(strip_tags($this->location));
        $this->price = htmlspecialchars(strip_tags($this->price));
        $this->created_at = htmlspecialchars(strip_tags($this->created_at));
        $this->approved = htmlspecialchars(strip_tags($this->approved));
        $this->idUser = htmlspecialchars(strip_tags($this->idUser));

        // bind data
        $stmt->bindParam(":id", $this->id);
        $stmt->bindParam(":ad_reference", $this->ad_reference);
        $stmt->bindParam(":title", $this->title);
        $stmt->bindParam(":photo", $this->photo);
        $stmt->bindParam(":description", $this->description);
        $stmt->bindParam(":place", $this->place);
        $stmt->bindParam(":location", $this->location);
        $stmt->bindParam(":price", $this->price);
        $stmt->bindParam(":created_at", $this->created_at);
        $stmt->bindParam(":approved", $this->approved);
        $stmt->bindParam(":idUser", $this->idUser);

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
}
