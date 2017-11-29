import java.util.Scanner;

class Yams{
	static Scanner in = new Scanner (System.in);

	static void init_simple_tab (int [] tab, int n) {

		int		i;

		i = 0;
		while (i < tab.length)
			tab[i++] = n;
	}

	static void init_tab_score (int [] [] tab_score) {

		int		i;

		i = 0;
		while (i < tab_score.length)
			init_simple_tab(tab_score[i++], -1);
	}

	/*static void sort_tab_die (int [] tab_die) {

		boolean		test;
		int			i;

		i = 0;
		test = false;
		while (test)
			while (i < tab_die.length - 1)
				if (tab_die[i] = tab_die[i + 1])
					test = false;
	} */

	static void init_tab_firstname (String [] tab_firstname, int nb_player) {

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

	static void contracts_list (int [] [] tab_score, int index, String tab_firstname []) {

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
				System.out.println("(13) Chance : Somme des 5 dés\n\n");
	}

	static boolean relaunch_the_die (int [] tab_die, int nb_die) {

		Scanner	sc = new Scanner (System.in);
		String	tmp;
		int		test;

		test = 1;
		while (test != 0)
		{
			System.out.print("Voulez-vous relancer le dé nº" + ( nb_die + 1) + " ? (oui / non) : ");
			tmp = sc.nextLine();
			if ((test = tmp.compareToIgnoreCase("oui")) != 0)
			{
				if ((test = tmp.compareToIgnoreCase("non")) == 0)
					return (false);
			}
			else
				return (true);
		}
		return (false);
	}

	static boolean retry () {

		int		test;
		String	tmp;
		Scanner	sc = new Scanner (System.in);

		test = 1;
		while (test != 0)
		{
			System.out.print("Voulez-vous relancer au moins un dé ? (oui / non) :");
			tmp = sc.nextLine();
			if ((test = tmp.compareToIgnoreCase("oui")) != 0)
			{
				if ((test = tmp.compareToIgnoreCase("non")) == 0)
					return(false);
			}
			else
				return (true);
		}
		return (false);
	}

	static int [] time_to_play (int [] [] tab_score, int index) {

		int [] tab_die = new int [5];
		int nb_essai;
		int nb_die;

		nb_essai = 1;
		while (nb_essai <= 3)
		{
			nb_die = 0;
			if (nb_essai == 1)
				while (nb_die <= 4)
					tab_die[nb_die++] = (int)(Math.random() * 6 + 1);
			nb_die = 0;
			System.out.println("Les dés affichent  : " + tab_die [0] + ", " + tab_die[1] + ", " + tab_die[2] + ", " + tab_die[3] + ", " + tab_die[4]);
			if (nb_essai < 3) 
			{
				if (!retry())
					break;
				while (nb_die <= 4)
				{
					if (relaunch_the_die(tab_die, nb_die))
						tab_die[nb_die] = (int)(Math.random() * 6 + 1);
					nb_die++;
				}
			}
			nb_essai++;
		}
		return (tab_die);
	}

	static void ft_nbocc (int [] tab_die, int [] nb_occ) {

			int		i;
			int		x;

			i = 0;
			x = 1;
			while (x <= 6)
			{
				while (i < tab_die.length)
				{
					if (x == tab_die[i])
						nb_occ[x-1]++;
					i++;
				}
				x++;
				i = 0;
			}
		}

	static int sum_tab (int [] tab_die) {

		int		i;
		int		x;

		i = 0;
		x = 0;
		while (i < tab_die.length)
			x = tab_die[i++] + x;
		return (x);
	}

	static void verify_ifc_ispo (int [] tab_die, int [] [] tab_score, int index, int contract_nb) {

		boolean		possible = false;
		boolean		fullPair = false;
		boolean		fullBre = false;
		int			i;
		int [] 		nb_occ = new int [6];

		init_simple_tab(nb_occ, 0);
		ft_nbocc(tab_die, nb_occ);
		i = 0;
		if (contract_nb == 6)
		{
			while (i < nb_occ.length)
			{
				if (nb_occ[i] >= 3)
				{
					possible = true;
					tab_score[contract_nb][index] = sum_tab(tab_die);
				}
				i++;
			}
		}
		else if (contract_nb == 8)
		{
			while (i < nb_occ.length)
			{
				if (nb_occ[i++] >= 4)
				{
					possible = true;
					tab_score[contract_nb][index] = sum_tab(tab_die);
				}
			}
		}
		else if (contract_nb == 7)
		{
			while (i < nb_occ.length)
				if (nb_occ[i++] >= 3)
					fullBre = true;
			i = 0;
			while (i < nb_occ.length)
				if (nb_occ[i++] >= 2)
					fullPair = true;
			if (fullPair && fullBre)
			{
				possible = true;
				tab_score[contract_nb][index] = 25;
			}
		}
		else if (contract_nb == 11)
		{
			while (i < nb_occ.length)
			{
				if (nb_occ[i++] == 5)
				{
					possible = true;
					tab_score[contract_nb][index] = 50;
				}
			}
		}
		if (!possible)
		{
			System.out.println("Ce contrat ne peut pas être rempli avec ces nombres.");
			tab_score[contract_nb][index] = -1;
			choose_contract(tab_die, tab_score, index);
		}
	}

	static int choose_contract (int [] tab_die, int [] [] tab_score, int index) {

		int		contract_nb;
		int		i;

		System.out.print("Quel contrat souhaitez-vous remplir ? (1 à 13) : ");
		contract_nb = in.nextInt();
		if (contract_nb < 1 || contract_nb > 13 || tab_score[contract_nb][index] != -1)
		{
			System.out.println("Merci d'en choisir un compris dans l'interval 1 à 13 et que vous n'avez pas encore validé.");
			contract_nb = choose_contract(tab_die, tab_score, index);
		}
		tab_score[contract_nb - 1][index]++;				//Mise à 0 du score pour le contrat selectionné par le joueur
		i = 0;
		if (contract_nb >= 1 && contract_nb <= 6)
		{
			while (i < tab_die.length)
			{
				if (tab_die[i] == contract_nb)
					tab_score[contract_nb - 1][index]++;
				i++;
			}
			tab_score[contract_nb - 1][index] = tab_score[contract_nb - 1][index] * (contract_nb);
		}
		else if (contract_nb == 13)
			while (i < tab_die.length)
				tab_score[contract_nb - 1][index] = tab_score[contract_nb - 1][index] + tab_die[i++];
		else if (contract_nb >= 7)
			verify_ifc_ispo(tab_die, tab_score, index, (contract_nb - 1));
		System.out.println("Score réaliser pour le contrat " + contract_nb + " : " + tab_score[contract_nb - 1][index]);
		return (contract_nb);
	}

	public static void main (String [] args){
	
		int		nb_player;
		int		index;
		int		test;
		int		contract_nb;
		String	tmp;
		Scanner	sc = new Scanner (System.in);
		int i;

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
		contract_nb = choose_contract(time_to_play(tab_score, index), tab_score, index);
	}
}
