<?php
	class Database
	{
		private $Host;
		private $User;
		private $Password;
		private $DbName;
		private $Port;
		public $dbh;
		
		public function __construct()
		{
			$this->Host ='localhost';
			$this->User ='id8007561_root';
			$this->Password='workshop';
			$this->DbName='id8007561_workshop';
			$this->Port='3306';
		}
		public function getConnection(){
            $dsn = 'mysql:dbname=' . $this->DbName . ';host=' . $this->Host.';port=' . $this->Port;
            try {
				$dbh = new PDO($dsn, $this->User, $this->Password);
				$this->dbh = $dbh;
				return $dbh;
            } catch (PDOException $e) {
				echo 'Connexion échouée : ' . $e->getMessage();
            }
				return false;
            }
	}
?>