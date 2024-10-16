# Supermercado App

O **Supermercado App** é um aplicativo Android inovador, desenvolvido para ajudar os usuários a gerenciar suas listas de compras de supermercado de forma prática e eficiente. 


Este projeto foi apresentado na **FETIN** (Feira de Tecnologia da Inatel), destacando suas funcionalidades e benefícios para o dia a dia dos consumidores.

## Funcionalidades

- **Adicionar Produtos**: Insira produtos com informações detalhadas, como nome, marca, preço, localização e quantidade, facilitando a organização das compras.
- **Visualizar Produtos**: A lista de produtos adicionados é exibida de forma clara e amigável, permitindo uma fácil visualização e acesso às informações.
- **Remover ou Diminuir Quantidade**: Permite ao usuário remover produtos da lista ou diminuir a quantidade disponível, adaptando-se às necessidades da compra.
- **Cálculo de Total**: O aplicativo calcula automaticamente o total da lista de compras, considerando a quantidade de cada produto, garantindo que o usuário tenha uma visão clara do valor total a ser gasto.
- **Enviar Lista via WhatsApp**: Facilita o compartilhamento da lista de produtos por WhatsApp, permitindo que o usuário envie a lista para um número de telefone especificado, agilizando o processo de compra.
- **Interface Moderna**: Utiliza o tema **Material3**, proporcionando uma experiência de usuário agradável e intuitiva, com design responsivo e fácil navegação.

## Tecnologias Utilizadas

- **Android SDK**: Ferramenta principal para o desenvolvimento do aplicativo Android, oferecendo recursos essenciais para a criação de aplicativos móveis.
- **Java**: Linguagem de programação utilizada para a lógica do aplicativo, garantindo robustez e eficiência.
- **SQLite**: Banco de dados leve utilizado para armazenar as informações da lista de produtos de forma rápida e eficaz.
- **Material Design**: Implementação de componentes de interface seguindo as diretrizes do Material Design, melhorando a experiência do usuário.

## Estrutura do Projeto

```plaintext
SupermercadoApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── crud_operations/
│   │   │   │               ├── MainActivity.java
│   │   │   │               ├── MyListActivity.java
│   │   │   │               ├── ListActivity.java
│   │   │   │               └── model/
│   │   │   │                   └── Produtos.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── ...
│   │   │   └── AndroidManifest.xml
│   │   └── ...
└── ...
