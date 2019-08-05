package com.ggl.mongo.dal;

import java.util.List;

import com.ggl.mongo.model.OwnTree;
import com.ggl.mongo.model.PrivateTree;
import com.ggl.mongo.model.Publictree;
import com.ggl.mongo.model.RandomNumber;
import com.ggl.mongo.model.TempOwnTree;
import com.ggl.mongo.model.TempPrivateTree;
import com.ggl.mongo.model.TempPublicTree;
import java.nio.file.Path;
//import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
public interface PublicTreeDAL {

	// Public Tree
	public String insertTempPublicUser(TempPublicTree temppublictree);
	public String insertTempPrivateTreeUser(TempPrivateTree temprivate);
	public String insertTempOwnTreeUser(TempOwnTree tempowntree);

	
	
	public List<TempPublicTree> getTempPublicTree();
	public List<TempPrivateTree> getTempPrivateTree();
	public List<TempOwnTree> getTempOwnTree();
	public TempPrivateTree getTempPrivateSingleUnitTree(String invoiceCode);
	public TempOwnTree getTempOwnSingleUnitTree(String invoiceCode);

	public TempPublicTree validatePublic(String tempInvoiceNumber);
	public TempPrivateTree validatePrivate(String tempInvoiceNumber);
	public TempOwnTree validateOwn(String tempInvoiceNumber);

	
	
	public boolean RemoveTempPrivateSingleUnitTree(TempPrivateTree temptree);
	public boolean RemoveTempOwnSingleUnitTree(TempOwnTree temptree);

	
	public TempPublicTree getTempPublicSingleUnitTree(String invoiceCode);
	public boolean RemoveTempPublicSingleUnitTree(TempPublicTree temptree);

	public String insertUser(Publictree publictree);
	public List<Publictree> getSinglePurchaseUnitByUserId(String primaryKey);
	public Publictree get8ComeOneOut();
	public String updateOutNumber(Publictree publictree, int userID);

	// Private Tree
	
	public String insertPrivateUser(PrivateTree privatetree);
	public List<PrivateTree> getSinglePrivatePurchaseUnitByUserId(String primaryKey,String treeName);	
	public String validateOwnTreeName(String primaryKey);
	public PrivateTree getPrivate8ComeOneOut();
	public String updatePrivateOutNumber(PrivateTree privatetree);

	// Own Tree

	public String createOwnTree(OwnTree owntree);
	public List<OwnTree> getSingleOwnPurchaseUnitByUserId(String primaryKey);
	public PrivateTree getOwn8ComeOneOut();
	public String updateOwnOutNumber(OwnTree owntree);
	public List<OwnTree> loadTreeName();

	
	// For Own Tree Random Number 
	public  RandomNumber getAllOwnTreeRandomNumber();
	// For update Own Tree Random Number
	public String updateOwnTreeRandomNumber(RandomNumber rn);
	
	// For Private member Random 
	public OwnTree getAllPrivateTreeRandomNumber(String refID);
	// For update Own Tree Random Number
	public String updatePrivateTreeRandomNumber(OwnTree rn);
	
	public String storeImage(MultipartFile file ,String invoiceNumber,String treeName);
	public Stream<Path> loadPublicImage();
	public Stream<Path> loadPrivateImage();
	public Stream<Path> loadOwnImage();
	public Resource loadPublicFile(String filename);
	public Resource loadPrivateFile(String filename);
	public Resource loadOwnFile(String filename);
	
	// Reject Public Tree
	public TempPublicTree rejectPublicUnit(String invoiceCode);
	public TempPrivateTree rejectPrivatecUnit(String invoiceCode);
	public TempOwnTree rejectOwnTree(String invoiceCode);

}