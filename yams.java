import java.util.Scanner;

class Yams{
	static Scanner in = new Scanner (System.in);

	public static void init_tab_score (int [] [] tab_score){

		int		i;
		int		y;

		i = 0;
		while (i < tab_score.length)
		{
			y = 0;
			while (y < tab_score[i].length)
				tab_score[i][y++] = -1;
			i++;
		}
	}

	public static void init_tab_firstname (String [] tab_firstname, int nb_player){

		int		i;
		Scanner sc = new Scanner (System.in);

		i = 0;
		while (i < tab_firstname.length)
		{
			System.out.print("Entrez le nom du joueur numero " + (i + 1) + " : ");
			tab_firstname[i++] = sc.nextLine();
		}
		System.out.print("\nNous avons donc " + nb_player + " joueur");
		if (nb_player == 1)
			System.out.println(" :");
		else 
			System.out.println("s :");
		i = 0;
		while (i < tab_firstname.length)
			System.out.println(tab_firstname[i++]);
	}

	public static void contracts_list (int [] [] tab_score, int index, String tab_firstname []) {

		System.out.println("\n" + tab_firstname[index] + " doit encore réaliser les contrats suivants :");
		if (tab_score[0][index] < 0)
				System.out.println("(1) Les 1 : Somme des 1");
		if (tab_score[1][index] < 0)
				System.out.println("(2) Les 2 : Somme des 2");
		if (tab_score[2][index] < 0)
				System.out.println("(3) Les 3 : Somme des 3");
		if (tab_score[3][index] < 0)
				System.out.println("(4) Les 4 : Somme des 4");
		if (tab_score[4][index] < 0)
				System.out.println("(5) Les 5 : Somme des 4");
		if (tab_score[5][index] < 0)
				System.out.println("(6) Les 6 : Somme des 6");
		if (tab_score[6][index] < 0)
				System.out.println("(7) Brelan : au moins 3 dés identiques - somme des 5 dés");
		if (tab_score[7][index] < 0)
				System.out.println("(8) Full : 3 dés identiques + 2 autres dés identiques = 25 points");
		if (tab_score[8][index] < 0)
				System.out.println("(9) Carré : au moins 4 dés identiques - somme des 5 dés");
		if (tab_score[9][index] < 0)
				System.out.println("(10) Petite suite : 4 dés qui se suivent (1,2,3,4 ou 2,3,4,5 ou 3,4,5,6) - 30 points");
		if (tab_score[10][index] < 0)
				System.out.println("(11) Grande suite : 5 dés qui se suivent (1,2,3,4,5 ou 2,3,4,5,6) - 40 points");
		if (tab_score[11][index] < 0)
				System.out.println("(12) Yams : 5 dés identiques - 50 points");
		if (tab_score[12][index] < 0)
				System.out.println("(13) Chance : Somme des 5 dés");
	}

	public static void time_to_play (int [] [] tab_score, int index) {

		int [] tab_die = new int [5];
		int nb_essai;
		int score;
		int contract_nb;
		int nb_die;
	}
	
	public static void main (String [] args){
	
		int		nb_player;
		int		index;
		int		test;
		String	tmp;
		Scanner	sc = new Scanner (System.in);

		System.out.println("Bonjour et bienvenue dans le jeu du YAMS.\nLes règles sont simples :\nChaque joueur a 3 lancés de dé par tour, et il y a 13 tours.\nÀ chaque lancé de dé, vous aurez le choix de garder ou non l'un ou plusieurs des 5 dés.\nLe but étant de faire un maximum de point en remplissant les contrats.\n");
		System.out.print("Entrez le nombre de joueurs : ");
		nb_player = in.nextInt();

		int [] [] tab_score = new int [15] [nb_player];
		init_tab_score(tab_score);
		String [] tab_firstname = new String [nb_player];
		init_tab_firstname(tab_firstname, nb_player);
		index = 0;
		System.out.println("C'est à " + tab_firstname[index] + " de jouer");
		test = 1;
		while (test != 0)
		{
			System.out.print("Entrez le mot \"lancer\" pour lancer les dés : ");
			tmp = sc.nextLine();
			test = tmp.compareToIgnoreCase("lancer");
		}
		contracts_list(tab_score, index, tab_firstname);
	}
}
