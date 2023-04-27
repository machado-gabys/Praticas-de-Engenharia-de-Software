import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ContaCorrente> contas = new ArrayList<>();
        //Inicio do ATM - seleionar ou criar nova conta
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Selecionar conta");
            System.out.println("2 - Criar nova conta");
            System.out.println("3 - Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o número da conta:");
                    int numeroConta = scanner.nextInt();
                    ContaCorrente contaSelecionada = null;
                    for (ContaCorrente conta : contas) {
                        if (conta.getNumero() == numeroConta) {
                            contaSelecionada = conta;
                            break;
                        }
                    }
                    if (contaSelecionada == null) {
                        System.out.println("Conta não encontrada.");
                    } else {
                        realizarOperacoes(contaSelecionada, scanner, contas);   //passa para o realizar operação se conta selecionada existe
                    }
                    break;
                case 2:
                    System.out.println("Digite o nome do titular:");
                    scanner.nextLine(); // consumi o \n deixado pelo nextInt
                    String nomeTitular = scanner.nextLine();
                    System.out.println("Digite o número da agência:");
                    int numeroAgencia = scanner.nextInt();
                    System.out.println("Digite o número da conta:");
                    int novoNumeroConta = scanner.nextInt();
                    Pessoa novoTitular = new Pessoa(nomeTitular);
                    ContaCorrente novaConta = new ContaCorrente(novoNumeroConta, numeroAgencia, novoTitular);
                    contas.add(novaConta);
                    System.out.println("Conta criada com sucesso. Número da conta: " + novoNumeroConta);
                    realizarOperacoes(novaConta, scanner, contas); //seleciona nova conta criada e envia para o realizar operações
                    break;
                case 3:
                    System.out.println("Obrigado por utilizar o ATM.");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    private static void realizarOperacoes(ContaCorrente contas, Scanner scanner, ArrayList<ContaCorrente> conta) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Exibir Saldo");
            System.out.println("2 - Realizar Depósito");
            System.out.println("3 - Realizar Saque");
            System.out.println("4 - Realizar Transferência");
            System.out.println("5 - Emitir Extrato");
            System.out.println("6 - Voltar");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Saldo da conta " + contas.getNumero() + ": " + contas.getSaldo());
                break;
            case 2:
                System.out.println("Digite o valor do depósito:");
                double valorDeposito = scanner.nextDouble();
                contas.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso.");
                break;
            case 3:
                System.out.println("Digite o valor do saque:");
                double valorSaque = scanner.nextDouble();
                boolean saqueRealizado = contas.sacar(valorSaque);
                if (saqueRealizado) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
                break;
            case 4:
                System.out.println("Digite o valor da transferência:");
                double valorTransferencia = scanner.nextDouble();
                System.out.println("Digite o número da conta de destino:");
                ContaCorrente contaDestino = null;
                int numeroContaDestino = scanner.nextInt();
                
                for (ContaCorrente contaIterada : conta) {
                    if (contaIterada.getNumero() == numeroContaDestino) {
                        contaDestino = contaIterada;
                        break;
                    }
                }
                if (contaDestino == null) {
                    System.out.println("Conta de destino não encontrada.");
                } else {
                    boolean transferenciaRealizada = contas.transferir(valorTransferencia, contaDestino);
                    if (transferenciaRealizada) {
                        System.out.println("Transferência realizada com sucesso.");
                    } else {
                        System.out.println("Saldo insuficiente.");
                    }
                }
                break;
            case 5:
                ArrayList<String> extrato = contas.getExtrato();
                for (String transacao : extrato) {
                    System.out.println(transacao);
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                break;
        }
    }
}
}




