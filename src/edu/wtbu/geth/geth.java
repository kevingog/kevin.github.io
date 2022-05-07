package edu.wtbu.geth;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;

public class geth {
	public static HttpService service = new HttpService("http://127.0.0.1:8545/");
	public static Admin admin=null;
	public static Web3j web3j=null;
	static {
		admin = Admin.build(service);
		web3j=Web3j.build(service);
	}
	
	public static String SingUpUser(String password) {
		String address = null;
		Request<?, NewAccountIdentifier> request = admin.personalNewAccount(password);
		try {
			NewAccountIdentifier response = request.send();
			address = response.getAccountId();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;
	}

	public static String getbalance(String address)  {
		String balance ="0";
		Request<?, EthGetBalance> request = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST);
		BigInteger Weiblance;
		try {
			EthGetBalance response = request.send();
			Weiblance=response.getBalance();
			balance=Convert.fromWei(Weiblance.toString(), Convert.Unit.ETHER).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
	
	public static String sendTranAccount(String money,String address) {
		 unlockAccount();
		  String BaseAddress= getAddress();
		  String sendAddress=null;
		   BigInteger value=Convert.toWei(money, Convert.Unit.ETHER).toBigInteger();
		   Transaction transaction = Transaction.createEtherTransaction(BaseAddress, null, null, Contract.GAS_LIMIT, address, value);
		    Request<?, EthSendTransaction> request = web3j.ethSendTransaction(transaction);
		    try {
				EthSendTransaction response = request.sendAsync().get();
				sendAddress=response.getResult();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return sendAddress;
	}
    
	public static String getAddress() {
	String address=null;
	Request<?, EthAccounts> request= admin.ethAccounts();
	try {
		EthAccounts responce = request.send();
		address=responce.getAccounts().get(0);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return address;
	}
	public static void unlockAccount() {
		Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(getAddress(), "123456");
		try {
			request.send();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
