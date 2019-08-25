# movienatic
Android App cujo objetivo é manter os usuários atualizados sobre os novos filmes.

### Como instalar

A aplicação pode ser executada com facilidade apenas executando __git clone__ do projeto, realizando um __git checkout develop__ dentro da pasta do projeto, abrir ele no Android Studio 3.5 e executá-lo em algum smartphone ou emulador Android.

### Issues

 * Ambas as telas tem muito a ser melhoradas. Todas elas poderiam ter um melhor arranjo dos seus elementos
   * A tela de detalhes dos filmes poderia exibir os trailers e teasers dos filmes e poderia ter mais detalhamento
   * A tela de listagem poderia oferecer um refresh por swipe e ter uma barra de pesquisa

 * Os testes unitários do Mockk pararam de funcionar depois da atualização recente do Android Studio e ainda estão quebradas (Alguma coisa haver com a biblioteca ByteBuddy do Mockk e a VM Hotspot)

### Stack
 * MVVM
 * Repository
 * LiveData
 * Kotlin
 * Coroutines
 * Realm
 * Mockk

### Features
 * Rotacionamento
 * Auto paginação
 * Cache Offline de filmes



