import java.util.*;

class Personagem {
    String nome;
    int vida, vidaMax, stamina, staminaMax, dextreza, sorte;
    Arma arma;
    Armadura armadura;

    Personagem(String nome, int vida, int stamina, int dextreza, int sorte) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.stamina = stamina;
        this.staminaMax = stamina;
        this.dextreza = dextreza;
        this.sorte = sorte;
        this.arma = null;
        this.armadura = null;
    }
}

class Arma {
    String nome;
    int dano, velocidade, custostamina;

    Arma(String nome, int dano, int velocidade, int custostamina) {
        this.nome = nome;
        this.dano = dano;
        this.velocidade = velocidade;
        this.custostamina = custostamina;
    }
}

class Armadura {
    String nome;
    int defesa, custostamina, postura;

    Armadura(String nome, int defesa, int custostamina, int postura) {
        this.nome = nome;
        this.defesa = defesa;
        this.custostamina = custostamina;
        this.postura = postura;
    }
}

public class CombateEmTurno {
    static Scanner scan = new Scanner(System.in);
    static Random ran = new Random();

    public static void main(String[] args) {
        ArrayList<Personagem> personagens = new ArrayList<>();
        ArrayList<Arma> armas = new ArrayList<>();
        ArrayList<Armadura> armaduras = new ArrayList<>();

        System.out.println("=== RPG Combate em Turno ===");

        while (true) {
            System.out.println("\nEscolha a operação: Adicionar | Simular | Sair");
            String opcao = scan.nextLine().toLowerCase();

            switch (opcao) {
                case "adicionar":
                    adicionar(personagens, armas, armaduras);
                    break;
                case "simular":
                    if (personagens.size() < 2 || armas.size() < 1 || armaduras.size() < 1) {
                        System.out.println("Adicione pelo menos 2 personagens, 1 arma e 1 armadura!");
                    } else {
                        simular(personagens, armas, armaduras);
                    }
                    break;
                case "sair":
                    System.out.println("Saindo do jogo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    static void adicionar(ArrayList<Personagem> pList, ArrayList<Arma> aList, ArrayList<Armadura> arList) {
        String resp;
        do {
            System.out.print("O que deseja adicionar? (Personagem/Arma/Armadura): ");
            resp = scan.nextLine().toLowerCase();

            switch (resp) {
                case "personagem":
                    System.out.print("Nome: "); String n = scan.nextLine();
                    System.out.print("Vida: "); int v = scan.nextInt();
                    System.out.print("Stamina: "); int s = scan.nextInt();
                    System.out.print("Destreza: "); int d = scan.nextInt();
                    System.out.print("Sorte: "); int so = scan.nextInt(); scan.nextLine();
                    pList.add(new Personagem(n, v, s, d, so));
                    break;
                case "arma":
                    System.out.print("Nome: "); String na = scan.nextLine();
                    System.out.print("Dano: "); int da = scan.nextInt();
                    System.out.print("Velocidade: "); int vel = scan.nextInt();
                    System.out.print("Custo Stamina: "); int cs = scan.nextInt(); scan.nextLine();
                    aList.add(new Arma(na, da, vel, cs));
                    break;
                case "armadura":
                    System.out.print("Nome: "); String nm = scan.nextLine();
                    System.out.print("Defesa: "); int df = scan.nextInt();
                    System.out.print("Custo Stamina: "); int cs2 = scan.nextInt();
                    System.out.print("Postura: "); int po = scan.nextInt(); scan.nextLine();
                    arList.add(new Armadura(nm, df, cs2, po));
                    break;
                default:
                    System.out.println("Opção não reconhecida!");
            }

            System.out.print("Deseja adicionar mais? (sim/não): ");
            resp = scan.nextLine().toLowerCase();
        } while (resp.equals("sim"));
    }

    static Personagem selecionarPersonagem(ArrayList<Personagem> lista, String ord) {
        System.out.println("Escolha o " + ord + " personagem:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).nome);
        }
        int idx = scan.nextInt() - 1; scan.nextLine();
        return lista.get(idx);
    }

    static Arma selecionarArma(ArrayList<Arma> lista, String nome) {
        System.out.println("Escolha a arma de " + nome + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).nome);
        }
        int idx = scan.nextInt() - 1; scan.nextLine();
        return lista.get(idx);
    }

    static Armadura selecionarArmadura(ArrayList<Armadura> lista, String nome) {
        System.out.println("Escolha a armadura de " + nome + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).nome);
        }
        int idx = scan.nextInt() - 1; scan.nextLine();
        return lista.get(idx);
    }

    static void simular(ArrayList<Personagem> pList, ArrayList<Arma> aList, ArrayList<Armadura> arList) {
        Personagem p1 = selecionarPersonagem(pList, "primeiro");
        Personagem p2 = selecionarPersonagem(pList, "segundo");

        p1.arma = selecionarArma(aList, p1.nome);
        p2.arma = selecionarArma(aList, p2.nome);
        p1.armadura = selecionarArmadura(arList, p1.nome);
        p2.armadura = selecionarArmadura(arList, p2.nome);

        int postura1 = p1.armadura.postura, postura2 = p2.armadura.postura;
        int poções1 = 3, poções2 = 3;
        int turno1 = 0, turno2 = 0;
        boolean defesa1 = false, defesa2 = false, caido1 = false, caido2 = false;

        System.out.println("\n--- COMBATE INICIADO: " + p1.nome + " VS " + p2.nome + " ---");

        while (p1.vida > 0 && p2.vida > 0) {
            turno1 += p1.arma.velocidade;
            turno2 += p2.arma.velocidade;

            while (turno1 >= 10) {
                executarTurno(p1, p2, new int[]{poções1}, new boolean[]{defesa1}, new int[]{postura1}, new boolean[]{caido1});
                turno1 -= 10;
            }

            while (turno2 >= 10) {
                executarTurno(p2, p1, new int[]{poções2}, new boolean[]{defesa2}, new int[]{postura2}, new boolean[]{caido2});
                turno2 -= 10;
            }

            if (p1.vida <= 0) { System.out.println(p2.nome + " VENCEU!"); break; }
            if (p2.vida <= 0) { System.out.println(p1.nome + " VENCEU!"); break; }

            // Regeneração de postura
            postura1 = Math.min(postura1 + 2, p1.armadura.postura);
            postura2 = Math.min(postura2 + 2, p2.armadura.postura);

            // Regeneração de stamina
            p1.stamina = Math.min(p1.stamina + 3, p1.staminaMax);
            p2.stamina = Math.min(p2.stamina + 3, p2.staminaMax);
        }
    }

    static void executarTurno(Personagem atacante, Personagem defensor, int[] poções, boolean[] defesaAtiva, int[] postura, boolean[] caido) {
        if (caido[0]) { System.out.println(atacante.nome + " está caído e perdeu o turno!"); caido[0] = false; return; }

        System.out.println("\n" + atacante.nome + " - Vida: " + atacante.vida + " | Stamina: " + atacante.stamina + " | Postura: " + postura[0]);
        System.out.println("Escolha ação: 1-Ataque 2-Defesa 3-Esquiva 4-Poção");
        int acao = scan.nextInt(); scan.nextLine();

        switch (acao) {
            case 1: // Ataque
                if (atacante.stamina < atacante.arma.custostamina) { System.out.println("Stamina insuficiente para atacar!"); break; }
                atacante.stamina -= atacante.arma.custostamina;

                int chanceAcerto = 10 + atacante.dextreza - ran.nextInt(10) - ran.nextInt(atacante.sorte + 1);
                if (chanceAcerto >= 5) {
                    int dano = atacante.arma.dano;
                    int critChance = ran.nextInt(100);
                    if (critChance < atacante.sorte * 5) { dano *= 1.5; System.out.println("CRÍTICO!"); }
                    else if (critChance > 95) { dano /= 2; System.out.println("Ataque parcialmente falhou!"); }

                    if (defesaAtiva[0]) { dano = dano * (100 - defensor.armadura.postura) / 100; defesaAtiva[0] = false; System.out.println(defensor.nome + " defendeu!"); }

                    dano -= defensor.armadura.defesa;
                    if (dano < 0) dano = 0;

                    defensor.vida -= dano;
                    postura[0] -= atacante.arma.velocidade;

                    if (postura[0] <= 0) { caido[0] = true; postura[0] = 0; System.out.println(defensor.nome + " caiu!"); }

                    System.out.println(atacante.nome + " causou " + dano + " de dano em " + defensor.nome);
                } else System.out.println(atacante.nome + " errou o ataque!");
                break;

            case 2: // Defesa
                if (atacante.stamina < atacante.armadura.custostamina) { System.out.println("Stamina insuficiente para defender!"); defesaAtiva[0] = false; break; }
                atacante.stamina -= atacante.armadura.custostamina;
                defesaAtiva[0] = true;
                System.out.println(atacante.nome + " está defendendo.");
                break;

            case 3: // Esquiva
                if (ran.nextInt(100) < atacante.dextreza * 5) { System.out.println(atacante.nome + " esquivou com sucesso!"); defesaAtiva[0] = true; }
                else System.out.println(atacante.nome + " falhou na esquiva.");
                break;

            case 4: // Poção
                if (poções[0] > 0) { atacante.vida = Math.min(atacante.vida + 10, atacante.vidaMax); poções[0]--; System.out.println(atacante.nome + " usou poção!"); }
                else System.out.println("Sem poções restantes!");
                break;

            default: System.out.println("Ação inválida!");
        }
    }
}
