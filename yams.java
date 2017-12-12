/* ****************************************************** */
/*                                                        */
/*                                                        */
/*   yams.java             Github.com/HisagiKaze          */
/*                                                        */
/*   By: POINOT Paul-Aurian <poinot.p@gmail.com>          */
/*                                                        */
/*   Created: 2017/11/27 ‏‎16:51:38 by ppoinot              */
/*   Updated: 2017/12/11 19:50:42 by ppoinot              */
/*                                                        */
/* ****************************************************** */

import java.util.Scanner;
import java.util.Arrays;

class Yams
{
	static Scanner in = new Scanner (System.in);

/* ******************************************************* */
/*  clear_term use the command "clear" in the terminal     */
/*  without disturb the game. 							   */
/* ******************************************************* */

	static void clear_term () {

		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

/* ******************************************************* */
/*  IME checks if the user enter an INTEGER and not	       */
/*  something else instead. 							   */
/* ******************************************************* */

	static int IME (int i) {

		int		x;
		boolean btest;
		Scanner sc;

		x = 0;
		btest = false;
		sc = new Scanner (System.in);
		if (i == 1)
		{
			do {
				try {
					System.out.print("Entrer le nombre de joueurs : ");
					x = sc.nextInt();
					btest = true;
				}
				catch (java.util.InputMismatchException e) {
					System.out.println("Merci d'entrer un chiffre de type Integer.");
					btest = false;
					String purge = sc.next();
				}
			} while (btest == false);
			sc.reset();
		}
		else
		{
			do {
			try {
				System.out.print("Quel contrat souhaitez-vous remplir ? (1 à 13) : ");;
				x = sc.nextInt();
				btest = true;
			}
			catch (java.util.InputMismatchException e) {
				System.out.println("Merci d'entrer un chiffre de type Integer.");
				btest = false;
				String purge = sc.next();
			}
		} while (btest == false);
		sc.reset();
		}
		return (x);
	}

/* ******************************************************* */
/*  reverse_tab_arg reverses the arguments of an arrays &  */
/*  returns a new array (new_tab).						   */
/* ******************************************************* */

	static int [] [] reverse_tab_arg (int [] [] ancient_tab, int a, int b) {

		int	[] [] 	new_tab = new int [b] [a];
		int			i;
		int			y;
		
		i = 0;
		while (i < new_tab.length)
		{
			y = 0;
			while(y < new_tab[i].length)
			{
				new_tab[i][y] = ancient_tab[y][i];
				y++;
			}
			i++;
		}
		return (new_tab);
	}
 
/* ******************************************************* */
/*  print_tab_score displays the tab_score on the terminal */
/* 	Interesting add-on for the player.					   */
/* ******************************************************* */

	static void print_tab_score (int [] [] tab_score, String [] tab_firstname) {

		int	[] [] 	new_tab = reverse_tab_arg(tab_score, 15, tab_firstname.length);
		int			i;
		int			y;
		
		i = 0;
		while (i < new_tab.length)
		{
			y = 0;
			System.out.print("Scores pour " + tab_firstname[i] + " (-1 pour un contrat non-rempli) = | ");
			while (y < new_tab[i].length - 2)
			{
				System.out.print("" + new_tab[i][y] + " | ");
				y++;
			}
			System.out.println("\n");
			i++;
		}
	}

/* ******************************************************* */
/*  print_tab_wins displays the tab_wins on the terminal   */
/* 	A BONUS part.										   */
/* ******************************************************* */

	static void print_tab_wins (int [] tab_wins, String [] tab_firstname) {

		int		i;

		i = 0;
		System.out.print("\n");
		while (i < tab_wins.length)
		{
			System.out.println(tab_firstname[i] + " a " + tab_wins[i] + " partie(s) gagnée(s).");
			i++;
		}
	}

/* ******************************************************* */
/*  init_simple_tab initializes a simple interger array    */
/*  It is a commun fonction. 							   */
/* ******************************************************* */

	static void init_simple_tab (int [] tab, int n) {

		int		i;

		i = 0;
		while (i < tab.length)
			tab[i++] = n;
	}

/* ******************************************************* */
/*  init_tab_score initializes a double interger array     */
/*  It's NOT a commun fonction. All the array is set to -1 */
/*  [contract_number] [Current Player] 					   */
/* ******************************************************* */

	static void init_tab_score (int [] [] tab_score) {

		int		i;

		i = 0;
		while (i < tab_score.length)
			init_simple_tab(tab_score[i++], -1);
	}

/* ******************************************************* */
/*  init_tab_firstname initializes a simple array of       */
/*	String. It's NOT a commun fonction. All the array is   */
/*	set with player(s) firstname. 					  	   */
/* ******************************************************* */

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

/* ******************************************************* */
/*  sum_tab make the sum of an array and return an integer */
/*	(the sum of it).									   */
/* ******************************************************* */

	static int sum_tab (int [] tab) {

		int		i;
		int		x;

		i = 0;
		x = 0;
		while (i < tab.length)
			x = tab[i++] + x;
		return (x);
	}

/* ******************************************************* */
/*  bonus counts if the current player make 63 or more     */
/*	with the sum of contract 1 to 6 included.			   */
/* ******************************************************* */

	static void bonus (int [] [] tab_score, int i) {

		int		x;

		x = tab_score[0][i] + tab_score[1][i] + tab_score[2][i] + tab_score[3][i] + tab_score[4][i] + tab_score[5][i];
		if (x >= 63)
			tab_score[13][i] = 35;
		else
			tab_score[13][i] = 0;
	}

/* ******************************************************* */
/*  contracts_list displays all contracts available for    */
/*	the current player. If tab_score[contract_nb][index]   */
/*	equals -1 : the contact is available.			  	   */
/* ******************************************************* */

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
				System.out.println("(5) Les 5 : Somme des 5");
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

/* ******************************************************* */
/*  relaunch_the_die ask is the current player wants	   */
/*	relaunch the die number X. It checks if "oui" or "non  */
/*	is well written and return a boolean.			  	   */
/* ******************************************************* */

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

/* ******************************************************* */
/*  retry ask is the current player wants relauch at least */
/*	one die. It checks if "oui" or "non is also well	   */
/*	written and return a boolean.					  	   */
/* ******************************************************* */

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

/* ******************************************************* */
/*  time_to_play randomizes one die by one die and diplays */
/*	them together. It also count the number of try and it  */
/*	returns a simple array of integer (the array of dice). */
/* ******************************************************* */

	static int [] time_to_play (int [] [] tab_score, int index, int k) {

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

/* ******************************************************* */
/*  ft_nbocc counts the number of occurence of each die.   */
/*	It modify the simple array of integer nb_occ.		   */
/* ******************************************************* */

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

/* ******************************************************* */
/*  verify_ifc_ispo verify if a contract is possible or	   */
/*	not to fill. It returns nothing but modify tab_score.  */
/* ******************************************************* */

	static void verify_ifc_ispo (int [] tab_die, int [] [] tab_score, int index, int contract_nb) {

		Scanner		sc = new Scanner (System.in);
		String		tmp;
		boolean		possible = false;
		boolean		fullPair = false;
		boolean		fullBre = false;
		int			i;
		int			n;
		int			test;
		int [] 		nb_occ = new int [6];

		init_simple_tab(nb_occ, 0);
		ft_nbocc(tab_die, nb_occ);
		i = 0;
		if (contract_nb == 6) // CAUTION verify_ifc_ispo(..., contract_nb - 1);
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
		else if (contract_nb == 9 || contract_nb == 10)
		{
			n = 1;
			i = 1;
			Arrays.sort(tab_die);
			while (i < tab_die.length)
			{
				if ((tab_die[i] == tab_die[i - 1]) && i < 4)
					i++;
				if ((tab_die[i] - 1) == tab_die[i - 1])
					n++;
				i++;
			}
			if (n >= 4 && contract_nb == 9)
			{
				possible = true;
				tab_score[contract_nb][index] = 30;
			}
			if (n == 5 && contract_nb == 10)
			{
				possible = true;
				tab_score[contract_nb][index] = 40;
			}
		}
		if (!possible)
		{
			System.out.println("");
			test = 1;
			while (test != 0)
			{
				System.out.print("Ce contrat ne peut pas être rempli avec ces nombres.\nÊtes-vous sûr de vouloir le sacrifier ? (oui / non) : ");
				tmp = sc.nextLine();
				if ((test = tmp.compareToIgnoreCase("oui")) != 0)
				{
					if ((test = tmp.compareToIgnoreCase("non")) == 0)
						{
							tab_score[contract_nb][index] = -1;
							choose_contract(tab_die, tab_score, index);
						}
				}
				else
						tab_score[contract_nb][index] = 0;
			}
		}
	}

/* ******************************************************* */
/*  choose_contract asks the current player wich contract  */
/*	he wants try to fill. If the contract number is not    */
/*	correct or unavailable, it re-calls itself (recursive) */
/*	It call verify_ifc_ispo if contract number is > or = 7 */
/*	but different to 13 (chance).						   */
/* ******************************************************* */

	static void choose_contract (int [] tab_die, int [] [] tab_score, int index) {

		int		contract_nb;
		int		i;

		contract_nb = IME(2);
		if (contract_nb < 1 || contract_nb > 13 || tab_score[contract_nb - 1][index] != -1)
		{
			System.out.println("Merci d'en choisir un compris dans l'interval 1 à 13 et que vous n'avez pas encore validé.");
			choose_contract(tab_die, tab_score, index);
			return ;
		}
		tab_score[contract_nb - 1][index]++;				//Set the score at 0 for the contract selected by the player
		i = 0;
		if (contract_nb >= 1 && contract_nb <= 6)
		{
			while (i < tab_die.length)
			{
				if (tab_die[i++] == contract_nb)
					tab_score[contract_nb - 1][index]++;
			}
			tab_score[contract_nb - 1][index] = tab_score[contract_nb - 1][index] * (contract_nb);
		}
		else if (contract_nb == 13)
			while (i < tab_die.length)
				tab_score[contract_nb - 1][index] = tab_score[contract_nb - 1][index] + tab_die[i++];
		else if (contract_nb >= 7)
			verify_ifc_ispo(tab_die, tab_score, index, (contract_nb - 1));
		System.out.println("Score réaliser pour le contrat " + contract_nb + " : " + tab_score[contract_nb - 1][index]);
	}

/* ******************************************************* */
/*  game_over add the bonus (63 or more) to the tab score  */
/*  and displays the tab_score.							   */
/* ******************************************************* */

	static boolean game_over (int [] [] tab_score, String [] tab_firstname, int [] tab_wins) {

		int		test;
		int		i;
		int	[] [] tab_score_reversed;
		String	tmp;
		Scanner	sc = new Scanner (System.in);

		i = 0;
		tab_score_reversed = reverse_tab_arg(tab_score, 15, tab_score[14].length);
		while (i < tab_score_reversed.length)
		{
			bonus(tab_score, i);
			tab_score_reversed = reverse_tab_arg(tab_score, 15, tab_firstname.length);
			tab_score[14][i] = sum_tab(tab_score_reversed[i]) + 1;
			i++;
		}
		winner_name(tab_score, tab_firstname, tab_wins);
		print_tab_wins(tab_wins, tab_firstname);
		//print_tab_score (tab_score, tab_firstname);
		test = 1;
		while (test != 0)
		{
			System.out.print("Voulez-vous rejouer ? (oui / non) :");
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

/* ******************************************************* */
/*  winner_name search and display the winner of the game. */
/*  If there are several winners, it displays all of them. */
/* ******************************************************* */

	static int winner_name (int [] [] tab_score, String [] tab_firstname, int [] tab_wins) {

		int		x;
		int		i;
		int		nb_winner;
		int		[] who_win;

		i = 0;
		x = -2;
		nb_winner = 0;
		who_win = new int [tab_firstname.length];
		init_simple_tab(who_win, 0);
		while (i < tab_score[14].length)
		{
			if (x < tab_score[14][i])
				x = tab_score[14][i];
			i++;
		}
		i = 0;
		while (i < tab_score[14].length)
		{
			if (x == tab_score[14][i])
			{
				who_win[i] = 1;
				tab_wins[i]++;
				nb_winner++;
			}
			i++;
		}
		if (nb_winner == 1)
			System.out.print("\nLe vainqueur est ");
		else if (nb_winner > 1)
			System.out.print("Les vainqueurs ex aequo sont ");
		i = 0;
		while (i < who_win.length)
		{
			if (who_win[i] == 1)
				System.out.print(tab_firstname[i] + " ");
			i++;
		}
		return (nb_winner);
	}

/* ******************************************************* */
/*  The main fonction is where start and end the programm. */
/*	It ask the number of player. It also count the 		   */
/*	thirteen tours and show who is turn to play. It calls  */
/*	the main subfonctions.   							   */
/* ******************************************************* */

	public static void main (String [] args) {
	
		boolean	p_again;
		int		nb_player;
		int		index;
		int		test;
		int		contract_nb;
		int		i;
		int		[] tab_wins;
		int		[] [] tab_score;
		String	tmp;
		String	[] tab_firstname;
		Scanner	sc = new Scanner (System.in);

		System.out.println("Bonjour et bienvenue dans le jeu du YAMS.\nLes règles sont simples :\nChaque joueur a 3 lancés de dé par tour, et il y a 13 tours.\nÀ chaque lancé de dé, vous aurez le choix de garder ou non l'un ou plusieurs des 5 dés.\nLe but étant de faire un maximum de point en remplissant les contrats.\n");
		nb_player = IME(1);

		tab_score = new int [15] [nb_player];
		tab_firstname = new String [nb_player];
		tab_wins = new int [nb_player];
		init_tab_firstname(tab_firstname, nb_player);
		init_simple_tab(tab_wins, 0);
		p_again = true;
		while (p_again)
		{
			i = 0;
			init_tab_score(tab_score);
			while (i++ < 13)
			{
				index = 0;
				while (index < nb_player)
				{
					System.out.println("C'est à " + tab_firstname[index] + " de jouer");
					test = 1;
					while (test != 0)
					{
						System.out.print("Entrez le mot \"lancer\" pour lancer les dés : ");
						tmp = sc.nextLine();
						test = tmp.compareToIgnoreCase("lancer");
					}
					clear_term();
					contracts_list(tab_score, index, tab_firstname);
					choose_contract(time_to_play(tab_score, index, i), tab_score, index);
					System.out.println("\n");
					index++;
				}
				print_tab_score(tab_score, tab_firstname);
			}
			//winner_name(tab_score, tab_firstname, tab_wins);
			//print_tab_wins(tab_wins, tab_firstname);
			p_again = game_over(tab_score, tab_firstname, tab_wins);
		}
	}
}