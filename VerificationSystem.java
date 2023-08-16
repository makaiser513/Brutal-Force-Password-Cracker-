import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
public class VerificationSystem
{
   ArrayList<String> UID = new ArrayList<String>();
   ArrayList<String> HashPassword = new ArrayList<String>();
   public void SetUID(String pathname) throws IOException {
   }
   public void SetHashPassword(String pathname) throws IOException{
   }
   public String Hashfunction(String PasswordAndSalt){
   }
   private String bytesToHex1(byte[] md5Array) {
   }
   public boolean Verify(String input1, String input2){
   }
}
