# Tutorial: Como Gerar e Instalar o APK Release no Android (Windows)

Este tutorial foi criado para ajudar você a sempre gerar, limpar e instalar corretamente o APK de release do seu projeto Android no Windows, evitando os problemas mais comuns.

---

## 1. Feche o Android Studio e o Emulador

Antes de rodar comandos, feche:
- O Android Studio (todos os projetos abertos)
- Qualquer emulador Android rodando

---

## 2. Abra o Prompt de Comando (CMD) ou PowerShell

- Use o **CMD** ou o **PowerShell**.
- Navegue até a raiz do seu projeto, onde fica o arquivo `gradlew.bat`.

Exemplo:
```sh
cd C:\Users\Digo\Desktop\Projeto-Casa-Guido\casa_guido
```

---

## 3. Limpe a Build Anterior

No CMD:
```sh
gradlew.bat clean
```
No PowerShell:
```sh
.\gradlew.bat clean
```

> Se der erro dizendo que arquivos estão em uso, feche tudo e tente deletar manualmente a pasta `app/build` no Explorer.

---

## 4. Gere o APK de Release

No CMD:
```sh
gradlew.bat assembleRelease
```
No PowerShell:
```sh
.\gradlew.bat assembleRelease
```

O APK será gerado em:
```
app\build\outputs\apk\release\app-release.apk
```

---

## 5. Instale o APK no Celular

#### A. Via ADB (recomendado)

- Ative o modo desenvolvedor e depuração USB no seu celular.
- Conecte o celular via USB.
- No terminal (na pasta do APK):

```sh
adb install -r app\build\outputs\apk\release\app-release.apk
```

O `-r` serve para substituir o app existente sem desinstalar dados.

#### B. Manualmente

- Copie o arquivo `app-release.apk` para o celular.
- Abra o gerenciador de arquivos do Android e instale o APK.

---

## 6. Abra o App e Teste

- Procure o app no seu celular e abra normalmente.
- Se o app fechar sozinho, colete o erro usando o **logcat** e verifique se há mensagens de crash.

---

## 7. Dicas e Soluções de Problemas

- **Erro de arquivos em uso:** Feche o Android Studio, emuladores e tente deletar a pasta `build` manualmente.
- **Erro de Baseline Profile:** Adicione no `build.gradle`:
  ```groovy
  android {
      buildTypes {
          release {
              baselineProfileEnable = false
          }
      }
  }
  ```
- **Arquitetura incompatível:** Certifique-se de que o APK gerado é compatível com a arquitetura do seu celular (arm64-v8a, armeabi-v7a, x86, etc).
- **Permissões:** Ative a opção para instalar apps de fontes desconhecidas no Android.
- **Gradle não encontrado:** Sempre rode os comandos na pasta onde o arquivo `gradlew.bat` está localizado.

---

## 8. Referências

- [Documentação oficial Android - Gerar APK](https://developer.android.com/studio/run)
- [Como instalar APK via ADB](https://developer.android.com/studio/command-line/adb#installing)

---

Pronto! Repita esse passo a passo sempre que precisar gerar e instalar o APK de release do seu projeto Android.