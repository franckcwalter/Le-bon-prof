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
    public $isFav;

    // Db connection
    public function __construct($db)
    {
        $this->conn = $db;
    }

    // GET ALL
    public function getAds($idloggedInUser)
    {

        $idloggedInUser;

        $sqlQuery = "SELECT ad.id, ad_reference, title, photo, description, place, location, price, created_at, approved, ad.idUser, user.first_name, ";
        $sqlQuery .= "CASE WHEN favorite.idUser = :idloggedInUser THEN 1 ELSE 0 END as isFav ";
        $sqlQuery .= "FROM " . $this->db_table . " ";
        $sqlQuery .= "INNER JOIN user ON ad.idUser = user.id ";
        $sqlQuery .= "LEFT JOIN favorite ON ad.id = favorite.idAd AND favorite.idUser = :idloggedInUser ";

        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(":idloggedInUser", $idloggedInUser);
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
    public function getAd($idloggedInUser)
    {

        $sqlQuery =
            "SELECT
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
            ad.idUser,
            user.first_name,
        CASE WHEN favorite.idUser = :idloggedInUser THEN 1 ELSE 0 END as isFav
            FROM
                " . $this->db_table . "
            INNER JOIN user ON ad.idUser = user.id 
            LEFT JOIN favorite ON ad.id = favorite.idAd AND favorite.idUser = :idloggedInUser
            WHERE 
                ad.id = :idAd
            LIMIT 0,1";


        $stmt = $this->conn->prepare($sqlQuery);
        $stmt->bindParam(":idAd", $this->id);
        $stmt->bindParam(":idloggedInUser", $idloggedInUser);

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
        $this->isFav = $dataRow['isFav'];
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

    function toggleFav()
    {

        // Vérifier si la paire existe dans la table favorite
        $checkQuery = "SELECT COUNT(*) as count FROM favorite WHERE idUser = :idUser AND idAd = :idAd";
        $checkStmt = $this->conn->prepare($checkQuery);
        $checkStmt->bindParam(":idUser", $this->idUser);
        $checkStmt->bindParam(":idAd", $this->id);
        $checkStmt->execute();

        $result = $checkStmt->fetch(PDO::FETCH_ASSOC);
        $pairExists = $result['count'];

        if ($pairExists) {
            // La paire existe, donc supprimer la paire
            $deleteQuery = "DELETE FROM favorite WHERE idUser = :idUser AND idAd = :idAd";
            $deleteStmt = $this->conn->prepare($deleteQuery);
            $deleteStmt->bindParam(":idUser", $this->idUser);
            $deleteStmt->bindParam(":idAd", $this->id);
            $deleteStmt->execute();

            return true;
        } else {
            // La paire n'existe pas, donc ajouter la paire
            $insertQuery = "INSERT INTO favorite (idUser, idAd) VALUES (:idUser, :idAd)";
            $insertStmt = $this->conn->prepare($insertQuery);
            $insertStmt->bindParam(":idUser", $this->idUser);
            $insertStmt->bindParam(":idAd", $this->id);
            $insertStmt->execute();

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
