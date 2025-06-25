import java.util.Scanner;
import java.util.Random;

class Personagem {
    String nome;
    int vida;
    int stamina;
    int dextreza;
    int sorte;
	Arma arma;
	Armadura armadura;
    Personagem(String nome, int vida, int stamina, int dextreza, int sorte) {
        this.nome = nome;
        this.vida = vida;
        this.stamina = stamina;
        this.dextreza = dextreza;
        this.sorte = sorte;
	this.arma = null;
	this.armadura = null;

	
    }
}

class Arma {
    String nome;
    int dano;
    int velocidade;
    int custostamina;

    Arma(String nome, int dano, int velocidade, int custostamina) {
        this.nome = nome;
        this.dano = dano;
        this.velocidade = velocidade;
        this.custostamina = custostamina;
    }
}

class Armadura {
    String nome;
    int defesa;
    int custostamina;
    int postura;

    Armadura(String nome, int defesa, int custostamina, int postura) {
        this.nome = nome;
        this.defesa = defesa;
        this.custostamina = custostamina;
        this.postura = postura;
    }
}

public class CombateEmTurno {
    public static void main(String[] args) {
        Personagem[] personagens = new Personagem[10];
        Arma[] armas = new Arma[10];
        Armadura[] armaduras = new Armadura[10];

        int indice1 = 0;
        int indice2 = 0;
        int indice3 = 0;

        Scanner scan = new Scanner(System.in);
        String resposta;

        while (true) {
            System.out.println("Qual a operação desejada?");
            System.out.println("Adicionar/Simular");
            resposta = scan.nextLine().toLowerCase();

            if (resposta.equals("adicionar")) {
                    while (true) {
                    System.out.print("O que deseja adicionar? (Personagem/Arma/Armadura): ");
                    resposta = scan.nextLine().toLowerCase();

                    if (resposta.equals("personagem")) {
                        System.out.println("Digite o nome do personagem:");
                        String nome = scan.nextLine();
                        System.out.println("Digite a vida do personagem:");
                        int vida = scan.nextInt();
                        System.out.println("Digite a stamina do personagem:");
                        int stamina = scan.nextInt();
                        System.out.println("Digite a destreza do personagem:");
                        int dextreza = scan.nextInt();
                        System.out.println("Digite a sorte do personagem:");
                        int sorte = scan.nextInt();
                        scan.nextLine();  

                        Personagem personagemCriado = new Personagem(nome, vida, stamina, dextreza, sorte);
                        personagens[indice1] = personagemCriado;
                        indice1++;

                    } else if (resposta.equals("arma")) {
                        scan.nextLine();  
                        System.out.println("Digite o nome da arma:");
                        String nome = scan.nextLine();
                        System.out.println("Digite o dano da arma:");
                        int dano = scan.nextInt();
                        System.out.println("Digite a velocidade da arma:");
                        int velocidade = scan.nextInt();
                        System.out.println("Digite o custo de stamina da arma:");
                        int custostamina = scan.nextInt();
                        scan.nextLine();  

                        Arma armaCriada = new Arma(nome, dano, velocidade, custostamina);
                        armas[indice2] = armaCriada;
                        indice2++;

                    } else if (resposta.equals("armadura")) {
                        scan.nextLine();  
                        System.out.println("Digite o nome da armadura:");
                        String nome = scan.nextLine();
                        System.out.println("Digite a defesa da armadura:");
                        int defesa = scan.nextInt();
                        System.out.println("Digite o custo de stamina da armadura:");
                        int custostamina = scan.nextInt();
                        System.out.println("Digite a postura da armadura:");
                        int postura = scan.nextInt();
                        scan.nextLine();  

                        Armadura armaduraCriada = new Armadura(nome, defesa, custostamina, postura);
                        armaduras[indice3] = armaduraCriada;
                        indice3++;

                    } else {
                        System.out.println("Opção não reconhecida. Tente novamente.");
                        continue;
                    }

                    
                    System.out.println("Deseja adicionar mais alguma coisa? (sim/não)");
                    resposta = scan.nextLine().toLowerCase();
                    if (!resposta.equals("sim")) {
                        break;
                    }
                }

            } else if (resposta.equals("simular")) {
                
                System.out.println("Escolha o primeiro personagem:");
                for (int i = 0; i < indice1; i++) {
                    System.out.println((i + 1) + ". " + personagens[i].nome);
                }
                int personagem01 = scan.nextInt();

                System.out.println("Escolha o segundo personagem:");
                int personagem02 = scan.nextInt();
                Personagem personagem1 = personagens[personagem01 - 1];
                Personagem personagem2 = personagens[personagem02 - 1];

                System.out.println("Escolha a arma do primeiro personagem:");
                for (int i = 0; i < indice2; i++) {
                    System.out.println((i + 1) + ". " + armas[i].nome);
                }
                int arma01 = scan.nextInt();

                System.out.println("Escolha a arma do segundo personagem:");
                int arma02 = scan.nextInt();
                Arma arma1 = armas[arma01 - 1];
                Arma arma2 = armas[arma02 - 1];

                System.out.println("Escolha a armadura do primeiro personagem:");
                for (int i = 0; i < indice3; i++) {
                    System.out.println((i + 1) + ". " + armaduras[i].nome);
                }
                int armadura01 = scan.nextInt();

                System.out.println("Escolha a armadura do segundo personagem:");
                int armadura02 = scan.nextInt();
                Armadura armadura1 = armaduras[armadura01 - 1];
                Armadura armadura2 = armaduras[armadura02 - 1];

                System.out.println(personagem1.nome + " VS " + personagem2.nome);
Random ran = new Random();
int poções1 = 3;
int poções2 = 3;

boolean defesaAtiva1 = false;
boolean defesaAtiva2 = false;

boolean caido1 = false;
boolean caido2 = false;

int posturaAtual1 = personagem1.armadura.postura;
int posturaAtual2 = personagem2.armadura.postura;

int turno1 = 0;
int turno2 = 0;

Random ran = new Random();

while (personagem1.vida > 0 && personagem2.vida > 0) {
    turno1 += personagem1.arma.velocidade;
    turno2 += personagem2.arma.velocidade;

    boolean personagem1Atingido = false;
    boolean personagem2Atingido = false;

    // --- TURNO PERSONAGEM 1 ---
    while (turno1 >= 10) {
        if (caido1) {
            System.out.println(personagem1.nome + " está caído e perdeu o turno!");
            caido1 = false;
            turno1 -= 10;
            break;
        }

        System.out.println(personagem1.nome + " - Vida: " + personagem1.vida + " | Stamina: " + personagem1.stamina + " | Postura: " + posturaAtual1);
        System.out.println("Escolha sua ação:");
        System.out.println("1. Ataque");
        System.out.println("2. Defesa");
        System.out.println("3. Esquiva");
        System.out.println("4. Usar poção (" + poções1 + " restantes)");
        int acao = scan.nextInt();

        if (acao == 1) {
            if (personagem1.stamina < personagem1.arma.custostamina) {
                System.out.println("Stamina insuficiente para atacar!");
            } else {
                personagem1.stamina -= personagem1.arma.custostamina;

                int chanceAcerto = 10 + (defesaAtiva2 ? personagem2.dextreza / 2 : 0) - ran.nextInt(10) - ran.nextInt(personagem1.sorte + 1);
                if (chanceAcerto <= personagem1.dextreza) {
                    int dano = personagem1.arma.dano;

                    int chanceSorte = ran.nextInt(100);
                    if (chanceSorte < personagem1.sorte * 5) {
                        dano *= 1.5;
                        System.out.println("Ataque crítico!");
                    } else if (chanceSorte > 95) {
                        dano *= 0.5;
                        System.out.println("Ataque falhou parcialmente!");
                    }

                    if (defesaAtiva2) {
                        dano = dano * (100 - personagem2.armadura.postura) / 100;
                        defesaAtiva2 = false;
                        System.out.println(personagem2.nome + " defendeu e reduziu o dano.");
                    }

                    dano -= personagem2.armadura.defesa;
                    if (dano < 0) dano = 0;

                    personagem2.vida -= dano;
                    personagem2Atingido = true;

                    // Reduz postura do oponente
                    posturaAtual2 -= personagem1.arma.velocidade;
                    if (posturaAtual2 <= 0) {
                        caido2 = true;
                        posturaAtual2 = 0;
                        System.out.println(personagem2.nome + " teve a postura quebrada e caiu!");
                    }

                    System.out.println(personagem1.nome + " causou " + dano + " de dano em " + personagem2.nome);
                } else {
                    System.out.println(personagem1.nome + " errou o ataque!");
                }
            }
        } else if (acao == 2) {
            if (personagem1.stamina < personagem1.armadura.custostamina) {
                System.out.println("Stamina insuficiente para defender.");
                defesaAtiva1 = false;
            } else {
                personagem1.stamina -= personagem1.armadura.custostamina;
                defesaAtiva1 = true;
                System.out.println(personagem1.nome + " está defendendo.");
            }
        } else if (acao == 3) {
            System.out.println(personagem1.nome + " tentou esquivar (A SER IMPLEMENTADO).");
        } else if (acao == 4) {
            if (poções1 > 0) {
                personagem1.vida += 7;
                if (personagem1.vida > 100) personagem1.vida = 100;
                poções1--;
                System.out.println(personagem1.nome + " usou uma poção e recuperou 7 de vida.");
            } else {
                System.out.println("Sem poções restantes!");
            }
        }

        turno1 -= 10;
    }

    // --- TURNO PERSONAGEM 2 ---
    while (turno2 >= 10) {
        if (caido2) {
            System.out.println(personagem2.nome + " está caído e perdeu o turno!");
            caido2 = false;
            turno2 -= 10;
            break;
        }

        System.out.println(personagem2.nome + " - Vida: " + personagem2.vida + " | Stamina: " + personagem2.stamina + " | Postura: " + posturaAtual2);
        System.out.println("Escolha sua ação:");
        System.out.println("1. Ataque");
        System.out.println("2. Defesa");
        System.out.println("3. Esquiva");
        System.out.println("4. Usar poção (" + poções2 + " restantes)");
        int acao = scan.nextInt();

        if (acao == 1) {
            if (personagem2.stamina < personagem2.arma.custostamina) {
                System.out.println("Stamina insuficiente para atacar!");
            } else {
                personagem2.stamina -= personagem2.arma.custostamina;

                int chanceAcerto = 10 + (defesaAtiva1 ? personagem1.dextreza / 2 : 0) - ran.nextInt(10) - ran.nextInt(personagem2.sorte + 1);
                if (chanceAcerto <= personagem2.dextreza) {
                    int dano = personagem2.arma.dano;

                    int chanceSorte = ran.nextInt(100);
                    if (chanceSorte < personagem2.sorte * 5) {
                        dano *= 1.5;
                        System.out.println("Ataque crítico!");
                    } else if (chanceSorte > 95) {
                        dano *= 0.5;
                        System.out.println("Ataque falhou parcialmente!");
                    }

                    if (defesaAtiva1) {
                        dano = dano * (100 - personagem1.armadura.postura) / 100;
                        defesaAtiva1 = false;
                        System.out.println(personagem1.nome + " defendeu e reduziu o dano.");
                    }

                    dano -= personagem1.armadura.defesa;
                    if (dano < 0) dano = 0;

                    personagem1.vida -= dano;
                    personagem1Atingido = true;

                    posturaAtual1 -= personagem2.arma.velocidade;
                    if (posturaAtual1 <= 0) {
                        caido1 = true;
                        posturaAtual1 = 0;
                        System.out.println(personagem1.nome + " teve a postura quebrada e caiu!");
                    }

                    System.out.println(personagem2.nome + " causou " + dano + " de dano em " + personagem1.nome);
                } else {
                    System.out.println(personagem2.nome + " errou o ataque!");
                }
            }
        } else if (acao == 2) {
            if (personagem2.stamina < personagem2.armadura.custostamina) {
                System.out.println("Stamina insuficiente para defender.");
                defesaAtiva2 = false;
            } else {
                personagem2.stamina -= personagem2.armadura.custostamina;
                defesaAtiva2 = true;
                System.out.println(personagem2.nome + " está defendendo.");
            }
        } else if (acao == 3) {
            System.out.println(personagem2.nome + " tentou esquivar (efeito simbólico no momento).");
        } else if (acao == 4) {
            if (poções2 > 0) {
                personagem2.vida += 7;
                if (personagem2.vida > 100) personagem2.vida = 100;
                poções2--;
                System.out.println(personagem2.nome + " usou uma poção e recuperou 7 de vida.");
            } else {
                System.out.println("Sem poções restantes!");
            }
        }

        turno2 -= 10;
    }

    // Regenerar postura se não foi atacado
    if (!personagem1Atingido) {
        posturaAtual1 += 2;
        if (posturaAtual1 > personagem1.armadura.postura) posturaAtual1 = personagem1.armadura.postura;
    }

    if (!personagem2Atingido) {
        posturaAtual2 += 2;
        if (posturaAtual2 > personagem2.armadura.postura) posturaAtual2 = personagem2.armadura.postura;
    }

    // Verifica fim do combate
    if (personagem1.vida <= 0) {
        System.out.println(personagem2.nome + " venceu!");
        break;
    }

    if (personagem2.vida <= 0) {
        System.out.println(personagem1.nome + " venceu!");
        break;
    }
}




            } else {
                System.out.println("Não entendi");
            }
        }
        }
    }

