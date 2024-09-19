public class BruteForce {


    public String bruteForceDecrypt(String encrypt, char[] alphabet) {
        for (int key = 1; key < alphabet.length; key++){
            Cipher cipher=new Cipher();
            String descrypt= cipher.decrypt(encrypt,key);
            System.out.println("Попытка с ключом "+ key + descrypt);
        }
        return "BruteForce сработал, не благодари ";
    }
}
