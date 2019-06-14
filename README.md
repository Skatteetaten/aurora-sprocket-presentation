# aurora-sprocket-presentation

This repo contains slides and examples for introduction Sprocket

The presentation is published here:

* https://skatteetaten.github.io/aurora-sprocket-presentation/index.html

Notes/manuscripts exist here: : https://skatteetaten.github.io/aurora-sprocket-presentation/script.html 

The presentation exist in the "presentation"-folder and is created with reveal.js. 

The manuscript is in the notes sub folder and is written using asciidoc.

Publish documenation with:

    ./gradlew gitPublishPush
  
Prerequisites is that the documentation is build and that auth is correctly configured using env vars GRGIT_USER and GRGIT_PASS.

    export GRGIT_USER=...
    export GRGIT_PASS=(cat ~/.github_pass) # fish shell
    export GRGIT_PASS=$(cat ~/.github_pass) # sh
