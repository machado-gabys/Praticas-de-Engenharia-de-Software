import java.util.ArrayList;
import java.util.Date;
public class ContaCorrente {

    private double saldo;
    private int agencia;
    private int numero;
    private Pessoa cliente;
    private ArrayList<String> extrato;

    public ContaCorrente(int numero, int agencia, Pessoa cliente) {
        this.agencia = agencia;
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0;
        this.extrato = new ArrayList<>();
        inicializarExtrato();
    }

    private void inicializarExtrato() {
        String mensagem = "Conta criada";
        extrato.add(mensagem);
    }
    
    public int getAgencia() {
        return agencia;
    }
    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean depositar(double valor){
        if (valor > 0){
            saldo = this.saldo + valor;
            extrato.add("Depósito de " + valor + " em " + new Date());
            return true;
        }
        return false;
    
    }

    public boolean sacar(double valor) {
        if (this.saldo < valor) {
            return false;
        } else {
            this.saldo = this.saldo - valor;
            extrato.add("Saque de " + valor + " em " + new Date());
            return true;
        }
    }

    public boolean transferir(double valor, ContaCorrente contaDestino){
        if (valor > 0 && valor <= saldo){
            saldo = this.saldo - valor;
            contaDestino.depositar(valor);
            extrato.add("Transferência de " + valor + " para conta " + contaDestino.getNumero() + " em " + new Date());
            return true;
        }
        return false;
    }

    public ArrayList<String> getExtrato() {
        return extrato;
    }
    
  }