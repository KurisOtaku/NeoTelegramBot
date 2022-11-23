<h1 align="center">NeoTelegramBot (in Java)</h1>


<h4 align="left"> 
	🤖 NeoTelegramBot  ☕ <br>
  ✈️ Em construção...🚧
</h4>

### Features

- [ ] Documentação 😕
- [x] ApiNeoBot (Objeto principal)
- [x] getMe
- [x] getInstance
- [x] getInstanceNoOffset
- [x] send
- [x] sendDice
- [x] sendButton
- [x] sendButtonFly_url
- [x] sendButtonFly_switch_inline_current_chat
- [x] sendButtonFly_callback
- [x] sendButtonFly_callback
- [x] sendVerticalButtonFly_callback
- [x] editMessage
- [x] deleteMessage
- [x] sendMarkdown
- [x] sendAnswerInlineQuery
- [x] sendButton
- [x] sendReply
- [x] sendPhoto

### Observações
> Ainda existem muitas opções que funcionam apenas com `static`.

> Também faz uso de uma LIB feita por <a href="https://github.com/LuizHenriqueKS/"> Zul </a> mas logo será implementado.

## Instalação
> Recomendo não utilizar enquanto a documentação não for concluída! ~ Kuris

#### Primeiros passos
- Acesse o <a href="t.me/BotFather">BotFather</a> crie o bot e gere um token.
- Seu ID pode ser encontrado com o bot <a href="https://t.me/userinfobot">@userinfobot</a> (great <a href="https://github.com/nadam">nadam</a>!)

```java
   public static void main(String[] args) throws Throwable {
        String token = "123456:token_fornecido_por_@BotFather";
        long masterid = 12345678900l; //Seu ID no telegram
        long groupid = -masterid; //ID grupo ou qualquer id negativo.
        ApiNeoBot bot = new ApiNeoBot(token, masterid, groupid);
        TelegramUpdate recebida = bot.getInstance(); // Busca mensagens
        bot.send(recebida.chatID(), "Hello World! 🌎"); // Envia mensagens
    }
```
#### Primeiro erro?
Tente mandar ao menos uma mensagem pro bot antes de executar a linha do `bot.send`.😉
```log
Exception in thread "main" java.lang.NullPointerException
	at api.kuris.telegrambot.neo.TelegramUpdate.chatID(TelegramUpdate.java:89)
	at Newaii.main(Newaii.java:28)
```

## Colaborador
<a href="https://github.com/LuizHenriqueKS/"> ZUL </a>


### Autor
---

<a href="https://github.com/KurisOtaku/">
 <img style="border-radius: 50%;" src="https://s.gravatar.com/avatar/311c17c86d7951c14e945b9268518a7a?s=80" width="100px;" alt=""/>
 <br />
 <sub><b>Kuris</b></sub></a> </a>


Feito por Kuris
