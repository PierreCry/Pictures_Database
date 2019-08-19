package PROJET_BROUILLON;

import java.io.File;
import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Modele.ImageBI;
import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ControlerMDI {

	@FXML
	public ModeleTest modele;
	@FXML
	private Button homeGallery;
	@FXML
	public String temp_index;
	@FXML
	private AnchorPane AnchorP;
	@FXML
	private SplitPane SplitP;
	@FXML
	private AnchorPane SplitAnchor1;
	@FXML
	private ButtonBar ButtonB;

	@FXML
	private RadioButton MenuB1et;
	@FXML
	private RadioButton MenuB1ou;
	@FXML
	private RadioButton MenuB2et;
	@FXML
	private RadioButton MenuB2ou;
	@FXML
	private RadioButton MenuB3et;
	@FXML
	private RadioButton MenuB3ou;

	@FXML
	private ChoiceBox<String> MenuB1;
	@FXML
	private CheckBox MenuB21;
	@FXML
	private CheckBox MenuB22;
	@FXML
	private CheckBox MenuB23;
	@FXML
	private CheckBox MenuB24;
	@FXML
	private CheckBox MenuB25;
	@FXML
	private CheckBox MenuB3r;
	@FXML
	private CheckBox MenuB3v;
	@FXML
	private CheckBox MenuB3b;
	@FXML
	private CheckBox MenuB3c;
	@FXML
	private CheckBox MenuB3m;
	@FXML
	private CheckBox MenuB3o;
	@FXML
	private TextField MenuB4;
	@FXML
	private ChoiceBox<String> MenuB5;
	@FXML
	private Button MenuB6;
	@FXML
	private Button Button6;
	@FXML
	private TabPane TabP;
	@FXML
	private Tab TabGalerie;
	@FXML
	private AnchorPane AnchorPaneGalerie;
	@FXML
	private ScrollPane ScrollPaneGalerie;
	@FXML
	private TilePane TilePaneGalerie;
	@FXML
	private Tab ImageComplete;
	@FXML
	private SplitPane SplitPaneImgComplete;
	@FXML
	private Button Aide_recherche;
	@FXML
	private TextField Snom;
	@FXML
	private TextField Staille;
	@FXML
	private TextField Spoids;
	@FXML
	private TextArea Stags;
	@FXML
	private RadioButton Sfavoris;
	@FXML
	private ChoiceBox<Integer> Snote;
	@FXML
	private TextField Scolors;
	@FXML
	private TextField Sopen;
	@FXML
	private AnchorPane LeftImgComplete;
	@FXML
	private AnchorPane RightImgComplete;
	@FXML
	private ImageView ImageViewImgComplete;

	ToggleGroup toggleGroup1 = new ToggleGroup();
	ToggleGroup toggleGroup2 = new ToggleGroup();
	ToggleGroup toggleGroup3 = new ToggleGroup();
	ToggleGroup toggleGroup4 = new ToggleGroup();

	public String[] requete = new String[5];
	public ArrayList<String> ColorChoose = new ArrayList<String>(
			Arrays.asList("Rouge", "Bleu", "Vert", "Cyan", "Magenta", "Orange"));

	// Constructeur et lien avec le MODELE
	public ControlerMDI(ModeleTest modele) {
		this.modele = modele;
	}

	// Methode d'initialisation de tout les �l�ments de l'application
	@FXML
	public void initialize() {

		ajouter_image();
		ModifFXML();
		InjectImages(modele.Limages);
		r�sultat_requeteerche();
		aide_recherche();
		System.out.println("Displaying Images");

	}

	// Configuration des boutons pour le tri
	private void ModifFXML() {

		MenuB1et.setToggleGroup(toggleGroup1);
		MenuB1ou.setToggleGroup(toggleGroup1);
		MenuB2et.setToggleGroup(toggleGroup2);
		MenuB2ou.setToggleGroup(toggleGroup2);
		MenuB3et.setToggleGroup(toggleGroup3);
		MenuB3ou.setToggleGroup(toggleGroup3);
		MenuB1ou.setSelected(true);
		MenuB2ou.setSelected(true);
		MenuB3ou.setSelected(true);
		SplitP.setDividerPositions(0.1);
		Snote.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

		// Configuration des choix possibles pour les boutons
		MenuB1.getItems().addAll("Oui", "Non", "ND");
		MenuB5.getItems().addAll("Taille", "Poids", "Nombre d'ouverture", "Note", "ND");

		// Mise en place des choix par d�fault sur les boutons
		MenuB1.setValue("ND");
		MenuB5.setValue("Taille");

		// MenuB6.getStyleClass().add("val");

		homeGallery.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				TilePaneGalerie.getChildren().clear();
				InjectImages(modele.Limages);
			}
		});

	}

	// Configuration du bouton de validation
	private void new_contro() throws Exception {
		FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("AideRecherche.fxml"));
		// secondLoader.setController(ControlerAR.class);

		Stage stage = new Stage();
		// AnchorPane root = (AnchorPane)
		// FXMLLoader.load(getClass().getResource("AideRecherche.fxml"));

		Scene scene = new Scene(secondLoader.load(), 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	private void aide_recherche() {
		Aide_recherche.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					new_contro();
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		});

	}

	private void r�sultat_requeteerche() {
		MenuB6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ArrayList<CheckBox> EtoilesCB = new ArrayList<CheckBox>(
						Arrays.asList(MenuB21, MenuB22, MenuB23, MenuB24, MenuB25));
				ArrayList<CheckBox> ColorsCB = new ArrayList<CheckBox>(
						Arrays.asList(MenuB3r, MenuB3b, MenuB3v, MenuB3c, MenuB3m, MenuB3o));
				ArrayList<RadioButton> RB = new ArrayList<RadioButton>(Arrays.asList(MenuB1et, MenuB2et, MenuB3et));
				String MenuB2E = "";
				String MenuB3E = "";

				for (int i = 0; i < EtoilesCB.size(); i++) {
					if (EtoilesCB.get(i).isSelected() == true) {
						MenuB2E += (i + 1) + " ";
					}
				}
				for (int i = 0; i < ColorsCB.size(); i++) {
					if (ColorsCB.get(i).isSelected() == true) {
						MenuB3E += ColorChoose.get(i) + " ";
					}
				}

				String menub4 = "";
				if (MenuB2E.equals("")) {
					MenuB2E = "null";
				}
				if (MenuB3E.equals("")) {
					MenuB3E = "null";
				}

				if (MenuB4.getText() == null || MenuB4.getText().trim().isEmpty()) {
					menub4 = "null";

				} else {
					menub4 = MenuB4.getText();
				}
				String[] tab = { MenuB1.getSelectionModel().getSelectedItem(), MenuB2E, MenuB3E, menub4,
						MenuB5.getSelectionModel().getSelectedItem() };
				String ETOR = "";

				for (int i = 0; i < RB.size(); i++) {
					if (RB.get(i).isSelected() == true) {
						ETOR += "1" + " ";
					} else {
						ETOR += "0" + " ";
					}
				}

				buildListeTri(tab, ETOR);
				// ET -> 1 | OU -> 0
			};
		});

	}

	// Methode pour trier les images en fonction des choix sur les boutons

	private void buildListeTri(String[] requete, String ETOR) {
		HashSet<String> TriBin = new HashSet<String>();
		ArrayList<String> ANDOR = new ArrayList<String>(Arrays.asList(ETOR.split(" ")));
		String ETOUfav = ANDOR.get(0);
		String ETOUnote = ANDOR.get(1);
		String ETOUcouleur = ANDOR.get(2);

		HashSet<String> SetEveryImagesNameCopy = new HashSet<>(modele.LimagesPATH);
		ArrayList<String> RenvoiFinal = new ArrayList<String>();
		ArrayList<ImageBI> RenvoiFinalDisplay = new ArrayList<ImageBI>();

		HashSet<String> TriBinFav = new HashSet<String>();
		if (requete[0] == "Oui") {
			TriBinFav.addAll(modele.ImagesFav);
		} else if (requete[0] == "Non") {
			SetEveryImagesNameCopy.removeAll(modele.ImagesFav);
			TriBinFav.addAll(SetEveryImagesNameCopy);

		} else if (requete[0] == "ND") {
			TriBinFav.addAll(modele.SetEveryImagesName);
		}

		HashSet<String> TriBinNotes = new HashSet<String>();
		if (requete[1] != "null") {
			ArrayList<String> Notesrequetes = new ArrayList<String>(Arrays.asList(requete[1].split(" ")));
			for (int i = 0; i < Notesrequetes.size(); i++) {
				int tempNote = Integer.parseInt(Notesrequetes.get(i));
				if (tempNote == 1) {
					TriBinNotes.addAll(modele.SetImages1Etoile);
				} else if (tempNote == 2) {
					TriBinNotes.addAll(modele.SetImages2Etoile);
				} else if (tempNote == 3) {
					TriBinNotes.addAll(modele.SetImages3Etoile);
				} else if (tempNote == 4) {
					TriBinNotes.addAll(modele.SetImages4Etoile);
				} else {
					TriBinNotes.addAll(modele.SetImages5Etoile);
				}
			}
		}
		HashSet<String> TriBinCouleurs = new HashSet<String>();
		if (requete[2] != "null") {
			ArrayList<String> Couleursrequetes = new ArrayList<String>(Arrays.asList(requete[2].split(" ")));
			for (int i = 0; i < Couleursrequetes.size(); i++) {
				TriBinCouleurs.addAll(modele.ListeSetImagesCouleurs.get(ColorChoose.indexOf(Couleursrequetes.get(i))));
			}
		}

		HashSet<String> avecTags = new HashSet<String>();
		for (Map.Entry<String, HashSet<String>> entry : modele.MapTags.entrySet()) {
			avecTags.addAll(entry.getValue());
		}

		HashSet<String> sansTags = new HashSet<String>();
		HashSet<String> SetEveryImagesNameCopy2 = new HashSet<>(modele.LimagesPATH);
		SetEveryImagesNameCopy2.removeAll(avecTags);
		sansTags = SetEveryImagesNameCopy2;
		HashSet<String> TriBinTags = new HashSet<String>();

		if (requete[3] != "null") {
			ArrayList<String> Tagsrequetes = new ArrayList<String>(Arrays.asList(requete[3].split(" ")));
			for (int i = 0; i < Tagsrequetes.size(); i++) {
				if (modele.MapTags.containsKey(Tagsrequetes.get(i))) {
					TriBinTags.addAll(modele.MapTags.get(Tagsrequetes.get(i)));
				}

			}
		}

		TriBin.addAll(TriBinFav);
//		System.out.println((MenuB21.isSelected() + " " +  MenuB22.isSelected() + " " + MenuB23.isSelected()
//				+ " " + MenuB24.isSelected() + " " + MenuB25.isSelected()));
		if ((MenuB21.isSelected() || MenuB22.isSelected() || MenuB23.isSelected()
				|| MenuB24.isSelected() || MenuB25.isSelected())) {
//			System.out.println("inNOTES");
			if (ETOUfav.equals("1")) {
//				System.out.println("avant" + " " + TriBin);
				TriBin.retainAll(TriBinNotes);
//				System.out.println("avant" + " " + TriBin);
			} else {
				TriBin.addAll(TriBinNotes);
			}
		}
		if ((MenuB3r.isSelected() || MenuB3b.isSelected() || MenuB3v.isSelected()
				|| MenuB3c.isSelected() || MenuB3o.isSelected() || MenuB3b.isSelected())) {
//			System.out.println("inCOULEURS");

			if (ETOUnote.equals("1")) {
				TriBin.retainAll(TriBinCouleurs);
			} else {
				TriBin.addAll(TriBinCouleurs);
			}

		}

		if (!MenuB4.getText().equals("Disable")) {
//			System.out.println("inTAGS");

			if (requete[3] == "null") {
				if (ETOUcouleur.equals("1")) {
					TriBin.retainAll(sansTags);
				} else {
					TriBin.addAll(sansTags);
				}
			} else {
				if (ETOUcouleur.equals("1")) {
					TriBin.retainAll(TriBinTags);
				} else {
					TriBin.addAll(TriBinTags);
				}
			}
		}

		Map<Integer, HashSet<String>> MapEtoiles = new HashMap<Integer, HashSet<String>>();
		MapEtoiles.put(1, modele.SetImages1Etoile);
		MapEtoiles.put(2, modele.SetImages2Etoile);
		MapEtoiles.put(3, modele.SetImages3Etoile);
		MapEtoiles.put(4, modele.SetImages4Etoile);
		MapEtoiles.put(5, modele.SetImages5Etoile);

		if (requete[4] == "Taille" || requete[4] == "Poids") {
			for (Integer key : modele.MapImagesTaille.keySet()) {
				for (String s : TriBin) {
					if (modele.MapImagesTaille.get(key).contains(s)) {
						RenvoiFinal.add(s);
					}
				}
			}
		} else if (requete[4] == "Nombre d'ouverture") {
			for (Integer key : modele.MapImagesCptOpen.keySet()) {
				for (String s : TriBin) {
					if ((modele.MapImagesCptOpen.get(key).contains(s))) {
						RenvoiFinal.add(s);
					}
				}
			}
		} else if (requete[4] == "ND") {
			RenvoiFinal.addAll(TriBin);
			RenvoiFinal.sort(String::compareToIgnoreCase);

		} else if (requete[4] == "Note") {
			for (Integer key : MapEtoiles.keySet()) {
				for (String s : TriBin) {
					if (MapEtoiles.get(key).contains(s)) {
						RenvoiFinal.add(s);
					}
				}
			}
		}

		for (int i = 0; i < RenvoiFinal.size(); i++) {
			int indexTempImageBI = (modele.LimagesPATH.indexOf(RenvoiFinal.get(i))); // r�cup�re l'indice de l'image
																						// mod�le.
			RenvoiFinalDisplay.add(modele.Limages.get(indexTempImageBI));
		}
		TilePaneGalerie.getChildren().clear();
		// System.out.println("last" + " " + RenvoiFinalDisplay); // IMAGESBI DU TRI DE
		// L'UTILISATEUR
		InjectImages(RenvoiFinalDisplay);
	}

	// M�thode qui assure le chargement des images
	private void InjectImages(ArrayList<ImageBI> LimagesC) { // ajouter un param�tre �tant une liste de string que sont
																// les paths des images
		// � display.

		TilePaneGalerie.setPadding(new Insets(15, 15, 15, 15));
		TilePaneGalerie.setHgap(10);
		TilePaneGalerie.getStyleClass().add("tpg");

		for (int i = 0; i < LimagesC.size(); i++) {
			ImageView imageView;
			imageView = createImageView(LimagesC.get(i));
			imageView.setId(String.valueOf(i));
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) { // Au clic, changement de tab et
					// affichage de l'image
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						if (event.getClickCount() == 2) {
							TabP.getSelectionModel().selectNext(); // Change de tab
							String source2 = event.getPickResult().getIntersectedNode().getId();
							temp_index = source2;
							ImageView temp = new ImageView();
							final ImageBI img = LimagesC.get(Integer.parseInt(source2));
							Image tempI = new Image("file:" + img.path);
							BorderPane borderPane = new BorderPane();
							temp.setImage(tempI);
							temp.setStyle("-fx-background-color: BLACK");
							temp.setFitHeight(LeftImgComplete.getHeight());
							temp.setPreserveRatio(true);
							temp.setSmooth(true);
							temp.setCache(true);
							borderPane.setCenter(temp);
							borderPane.setStyle("-fx-background-color: BLACK");
							LeftImgComplete.getChildren().add(borderPane);
							LeftImgComplete.getChildren().clear();
							LeftImgComplete.getChildren().add(temp);

							Snom.textProperty().setValue(img.nom);
							Staille.textProperty().setValue(
									(int) Math.round(tempI.getWidth()) + " x " + (int) Math.round(tempI.getHeight()));
							Spoids.textProperty()
									.setValue(String.valueOf(Math.round(tempI.getWidth() * tempI.getHeight() * 4)));
							Stags.textProperty().setValue(img.show_Tags(img.mots_clefs));
							SplitPaneImgComplete.setDividerPositions(0.8f, 0.2f);
							img.Increase_nbOuverture();
							Sopen.setText(String.valueOf(img.nb_ouverture));

							Scolors.setText(img.couleur);
							Sfavoris.setSelected(img.favoris);
							Snote.setValue(img.etoile);

							Snom.setEditable(false);
							Staille.setEditable(false);
							Spoids.setEditable(false);
							Scolors.setEditable(false);
							Sopen.setEditable(false);

							@SuppressWarnings("unused")
							HashSet<String> toworkValue = new HashSet<String>();
							int toworkKey = 0;
							for (Integer key : modele.MapImagesCptOpen.keySet()) {
								if (modele.MapImagesCptOpen.get(key)
										.contains(LimagesC.get(Integer.parseInt(temp_index)).path)) {
									toworkValue = modele.MapImagesCptOpen.get(key);
									toworkKey = key;
								}

							}

							if (modele.MapImagesCptOpen.containsKey(toworkKey + 1)) {
								modele.MapImagesCptOpen.get(toworkKey + 1)
										.add(LimagesC.get(Integer.parseInt(temp_index)).path);
								modele.MapImagesCptOpen.get(toworkKey)
										.remove(LimagesC.get(Integer.parseInt(temp_index)).path);

							} else {
								HashSet<String> tempAdd = new HashSet<String>();
								tempAdd.add(modele.Limages.get(Integer.parseInt(temp_index)).path);
								modele.MapImagesCptOpen.put(toworkKey + 1, tempAdd);
								modele.MapImagesCptOpen.get(toworkKey)
										.remove(modele.Limages.get(Integer.parseInt(temp_index)).path);

							}
						}
					}
				}
			});

			// Configuration de la zone de texte pour les tags
			Stags.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						ArrayList<String> before = new ArrayList<>();
						before = LimagesC.get(Integer.parseInt(temp_index)).mots_clefs;
						String text = Stags.getText().trim().replaceAll("\n", "").replaceAll("\r", "")
								.replaceAll("\\s+", "");
						LimagesC.get(Integer.parseInt(temp_index)).Set_Tags(text);
						ArrayList<String> after = new ArrayList<>();
						after = LimagesC.get(Integer.parseInt(temp_index)).mots_clefs;
						// LimagesC.get(Integer.parseInt(temp_index)).mots_clefs);
						Tags_soustraction(Integer.parseInt(temp_index), before, after, LimagesC);
					}
				}
			});

			// Configuration du bouton qui g�re les favoris
			Sfavoris.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					int index = Integer.parseInt(temp_index);
					LimagesC.get(index).Set_Favoris();
					if (modele.ImagesFav.contains(LimagesC.get(index).path)) {
						modele.ImagesFav.remove(LimagesC.get(index).path);
					} else {
						modele.ImagesFav.add(LimagesC.get(index).path);

					}

				}
			});

			// Configuration de la boite qui permet de g�rer les notes
			Snote.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
				@Override

				public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
					if ((int) number == -1) {
						return;
					}
					ArrayList<HashSet<String>> temp = new ArrayList<HashSet<String>>(
							Arrays.asList(modele.SetImages1Etoile, modele.SetImages2Etoile, modele.SetImages3Etoile,
									modele.SetImages4Etoile, modele.SetImages5Etoile));

					LimagesC.get(Integer.parseInt(temp_index)).Set_Etoile((Integer) number2);
					LimagesC.get(Integer.parseInt(temp_index)).etoile = (Integer) number2 + 1;
					temp.get((int) number).remove(LimagesC.get(Integer.parseInt(temp_index)).path);
					temp.get((int) number2).add(LimagesC.get(Integer.parseInt(temp_index)).path);

				}

			});

			VBox vbox = new VBox();
			String nom = new String(LimagesC.get(i).path.toString().split("\\\\")[1].split("\\.")[0]);
			if (nom.length() > 15) {
				nom = nom.substring(0, 15) + "..." + LimagesC.get(i).path.toString().split("\\\\")[1].split("\\.")[1];
			} else {
				nom = nom + "." + LimagesC.get(i).path.toString().split("\\\\")[1].split("\\.")[1];
			}

			Label label1 = new Label(nom);
			label1.getStyleClass().add("label1");
			// Cr�ation de la VBox qui accueille l'imageView et le label1
			vbox.setSpacing(10);
			vbox.getChildren().addAll(imageView, label1);
			vbox.setAlignment(Pos.CENTER);
			vbox.getStyleClass().add("vbox");
			TilePaneGalerie.getChildren().addAll(vbox);
		}

		ScrollPaneGalerie.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		ScrollPaneGalerie.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		ScrollPaneGalerie.setFitToWidth(true);
		ScrollPaneGalerie.getStyleClass().add("spg");
		ScrollPaneGalerie.setContent(TilePaneGalerie);

	}

	// M�thode qui permet la cr�ation de l'imageView
	private ImageView createImageView(ImageBI img) {
		Image temp = new Image("file:" + img.path, 150, 0, true, true);
		ImageView imageView = new ImageView(temp);
		imageView.setFitWidth(150);
		imageView.getStyleClass().add("image");
		return imageView;
	}

	private void Tags_soustraction(int indeximg, ArrayList<String> A, ArrayList<String> B,
			ArrayList<ImageBI> LimagesC) {
		for (int i = 1; i < A.size(); i++) {
			HashSet<String> temp = (modele.MapTags.get(A.get(i)));
			if (!B.contains(A.get(i))) {
				temp.remove(LimagesC.get(indeximg).path);
			}
		}

		if (B.size() != A.size()) {
			HashSet<String> Aset = new HashSet<String>(A);
			HashSet<String> Bset = new HashSet<String>(B);
			Bset.removeAll(Aset);

			ArrayList<String> SetDiff = new ArrayList<String>();
			SetDiff.addAll(Bset);
			for (int j = 0; j < SetDiff.size(); j++) {
				if (modele.MapTags.containsKey(SetDiff.get(j))) {
					modele.MapTags.get(SetDiff.get(j)).add(LimagesC.get(indeximg).path);
				} else {
					HashSet<String> tempp = new HashSet<String>();
					tempp.add(LimagesC.get(indeximg).path);
					modele.MapTags.put(SetDiff.get(j), tempp);
				}
			}
		}

	}

	private void ajouter_image() {
		Button6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent e) {

				Stage stage = new Stage();
				stage.setTitle("Second Stage");

				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters()
						.addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*jpeg"));

				List<File> list = fileChooser.showOpenMultipleDialog(stage);

				ArrayList<String> newImageName = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					newImageName.add("Images/" + list.get(i).getName());
					try {
						Files.copy(FileSystems.getDefault().getPath(list.get(i).getPath()),
								FileSystems.getDefault().getPath(new File("Images/" + list.get(i).getName()).getPath()),
								StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				ArrayList<ImageBI> newImageBI = new ArrayList<ImageBI>();
				for (int i = 0; i < newImageName.size(); i++) {
					if (modele.LimagesPATH.contains(newImageName.get(i))) {
						newImageName.remove(i);
					} else {
						String temp = newImageName.get(i).toString();
						temp = temp.replace("/", "\\");
						ImageBI Imagetemp = new ImageBI(temp);
						newImageBI.add(Imagetemp);
					}
				}
				modele.InitData(newImageBI);
				modele.LimagesPATH.sort(String::compareToIgnoreCase);
				modele.Limages.addAll(newImageBI);
				Stream<ImageBI> T = modele.Limages.stream()
						.sorted((p1, p2) -> p1.path.toLowerCase().compareTo(p2.path.toLowerCase()));
				List<ImageBI> A = T.collect(Collectors.toList());
				ArrayList<ImageBI> temp = new ArrayList<ImageBI>(A);
				modele.Limages = temp;
				TilePaneGalerie.getChildren().clear();
				InjectImages(modele.Limages);
			}
		});
	}

}