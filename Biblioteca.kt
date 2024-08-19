// Esse é o Link para o Play-Kotlin https://pl.kotl.in/1jb5z7uHq

// Classe Livro
class Livro(val titulo: String, val autor: String) {
    var disponivel: Boolean = true

    fun exibirDetalhes() {
        println("Título: $titulo, Autor: $autor, Disponível: $disponivel")
    }
}

// Classe Usuario
class Usuario(val nome: String) {
    val livrosEmprestados = mutableListOf<Livro>()

    fun emprestarLivro(livro: Livro, biblioteca: Biblioteca) {
        if (biblioteca.emprestarLivro(livro)) {
            livrosEmprestados.add(livro)
            println("$nome emprestou o livro: ${livro.titulo}")
        } else {
            println("O livro ${livro.titulo} não está disponível.")
        }
    }

    fun devolverLivro(livro: Livro, biblioteca: Biblioteca) {
        if (livrosEmprestados.contains(livro)) {
            livrosEmprestados.remove(livro)
            biblioteca.devolverLivro(livro)
            println("$nome devolveu o livro: ${livro.titulo}")
        } else {
            println("$nome não tem o livro ${livro.titulo} para devolver.")
        }
    }
}

// Classe Biblioteca
class Biblioteca {
    private val livros = mutableListOf<Livro>()

    fun adicionarLivro(livro: Livro) {
        livros.add(livro)
    }

    fun exibirLivrosDisponiveis() {
        println("Livros disponíveis:")
        livros.filter { it.disponivel }.forEach { it.exibirDetalhes() }
    }

    fun emprestarLivro(livro: Livro): Boolean {
        return if (livro.disponivel) {
            livro.disponivel = false
            true
        } else {
            false
        }
    }

    fun devolverLivro(livro: Livro) {
        livro.disponivel = true
    }
}

// Função principal para testar o Sistema
fun main() {
    // Criando instâncias de livros
    val livro1 = Livro("Senhor dos Aneis", "Tolkien")
    val livro2 = Livro("Harry Potter", "JKrowling")

    // Criando instância da biblioteca e adicionando livros
    val biblioteca = Biblioteca()
    biblioteca.adicionarLivro(livro1)
    biblioteca.adicionarLivro(livro2)

    // Exibindo livros disponíveis antes do empréstimo
    biblioteca.exibirLivrosDisponiveis()

    // Criando instância de um usuário
    val usuario1 = Usuario("Wallace")

    // Empréstimo de livros
    usuario1.emprestarLivro(livro1, biblioteca)

    // Exibindo livros disponíveis após o empréstimo
    biblioteca.exibirLivrosDisponiveis()

    // Devolução de livros
    usuario1.devolverLivro(livro1, biblioteca)

    // Exibindo livros disponíveis após a devolução
    biblioteca.exibirLivrosDisponiveis()
}

