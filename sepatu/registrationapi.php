<?php   
  require_once 'connection.php';  
  $response = array();  
  if(isset($_GET['apicall'])){  
  switch($_GET['apicall']){  
  case 'signup':  
    if(isTheseParametersAvailable(array('username','email','password','gender'))){  
    $username = $_POST['username'];   
    $email = $_POST['email'];   
    $password = md5($_POST['password']);  
    $gender = $_POST['gender'];   
   
    $stmt = $conn->prepare("SELECT id FROM teble_login WHERE username = ? OR email = ?");  
    $stmt->bind_param("ss", $username, $email);  
    $stmt->execute();  
    $stmt->store_result();  
   
    if($stmt->num_rows > 0){  
        $response['error'] = true;  
        $response['message'] = 'User already registered';  
        $stmt->close();  
    }  
    else{  
        $stmt = $conn->prepare("INSERT INTO teble_login (username, email, password, gender) VALUES (?, ?, ?, ?)");  
        $stmt->bind_param("ssss", $username, $email, $password, $gender);  
   
        if($stmt->execute()){  
            $stmt = $conn->prepare("SELECT id, id, username, email, gender FROM teble_login WHERE username = ?");   
            $stmt->bind_param("s",$username);  
            $stmt->execute();  
            $stmt->bind_result($userid, $id, $username, $email, $gender);  
            $stmt->fetch();  
   
            $user = array(  
            'id'=>$id,   
            'username'=>$username,   
            'email'=>$email,  
            'gender'=>$gender  
            );  
   
            $stmt->close();  
   
            $response['error'] = false;   
            $response['message'] = 'User registered successfully';   
            $response['user'] = $user;   
        }  
    }  
   
}  
else{  
    $response['error'] = true;   
    $response['message'] = 'required parameters are not available';   
}  
break;   
case 'login':  
  if(isTheseParametersAvailable(array('username', 'password'))){  
    $username = $_POST['username'];  
    $password = md5($_POST['password']);   
   
    $stmt = $conn->prepare("SELECT id, username, email, gender FROM teble_login WHERE username = ? AND password = ?");  
    $stmt->bind_param("ss",$username, $password);  
    $stmt->execute();  
    $stmt->store_result();  
    if($stmt->num_rows > 0){  
    $stmt->bind_result($id, $username, $email, $gender);  
    $stmt->fetch();  
    $user = array(  
    'id'=>$id,   
    'username'=>$username,   
    'email'=>$email,  
    'gender'=>$gender  
    );  
   
    $response['error'] = false;   
    $response['message'] = 'Login successfull';   
    $response['user'] = $user;   
 }  
 else{  
    $response['error'] = false;   
    $response['message'] = 'Invalid username or password';  
 }  
}  
break;
case 'shop':  
  if(isTheseParametersAvailable(array('alamat', 'jumlah', 'total', 'pilihan'))){  
    $alamat = $_POST['alamat'];
    $jumlah = $_POST['jumlah'];  
    $total = $_POST['total'];   
    $pilihan = $_POST['pilihan'];
   
    $stmt = $conn->prepare("INSERT INTO shoping (alamat, jumlah, total, pilihan) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssss",$alamat, $jumlah, $total, $pilihan);  
    $stmt->execute();  
    $stmt->store_result();  
    if($stmt->num_rows > 0){  
    $stmt->bind_result($id_belanja, $alamat, $jumlah, $total, $pilihan);  
    $stmt->fetch();  
    $user = array(  
    'id_belanja'=>$id_belanja,   
    'alamat'=>$alamat,   
    'jumlah'=>$jumlah,  
    'total'=>$total,  
    'pilihan'=>$pilihan
    );  
   
    $response['error'] = false;   
    $response['message'] = 'Order Success';   
    $response['id_belanja'] = $id_belanja;   
 }  
 else{  
    $response['error'] = false;   
    $response['message'] = 'Order Success';  
 }  
}  
break;

default:   
 $response['error'] = true;   
 $response['message'] = 'Invalid Operation Called';  
}  
}  
else{  
 $response['error'] = true;   
 $response['message'] = 'Invalid API Call';  
}  
echo json_encode($response);  
function isTheseParametersAvailable($params){  
foreach($params as $param){  
 if(!isset($_POST[$param])){  
     return false;   
  }  
}  
return true;   
}  
?>  