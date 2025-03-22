[========================================]
[     BSAH - Between Server And Host     ]
[========================================]
[By: Delta~Cion (@nionim)                ]
[TG: https://t.me/projectviolette        ]
[Discord: https://discord.gg/MEBkvJbe4P  ]
[========================================]

Эта программа написана СТРОГО для запуска Minecraft серверов с флагами отличными от тех, что прописаны в панели хоста (Например Pterodactyl).
Это не позволит запускать сразу несколько игровых серверов. Но это поможет избежать вечной ебли с тех. поддержкой по поводу нужных вам флагов запуска.

Использование:
    Оригинальная строка запуска в startup.txt:
        java -Xms000M -Xmx0000M -jar server.jar nogui
    Целевая строка запуска (То как она должна выглядеть после вашей настройки):
        <Ваши флаги> -jar server.jar
    Мой пример строки (Желательно всё же это писать в одну строку. Но можно и как у меня):
        ==========================================================================================
        -DPaper.IgnoreJavaVersion=true -Dfml.queryResult=confirm -Dlog4j2.formatMsgNoLookups=true
        -Dterminal.jline=false -Dterminal.ansi=true -DIReallyKnowWhatIAmDoingISwear=true
        -Djdk.module.illegalAccess=deny -Duser.timezone=Europe/Moscow -Dfile.encoding=UTF-8
        -XX:+UseG1GC -XX:G1ReservePercent=5 -XX:G1HeapWastePercent=10 -XX:+OptimizeStringConcat
        -XX:+TieredCompilation -XX:+EliminateLocks -XX:+UseSuperWord -XX:+OptimizeFill
        -XX:LoopUnrollMin=4 -XX:LoopMaxUnroll=16 -XX:+UseLoopPredicate -XX:+RangeCheckElimination
        -XX:+DisableExplicitGC -XX:+UseFastJNIAccessors -jar Violette.jar
        ==========================================================================================
    Пример строки, которую я использовал при разработке:
        -jar D:\Desktop\TheVoid\JAVA\BSAH\build\libs\testDir\core\Violette.jar

    Что вам с ней делать?:
        Замените её на ваш стартап сервера.

        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        УДАЛИТЕ -> "java -Xms000M -Xmx0000M" и "nogui"
        УДАЛИТЕ -> ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        ^^^^^^^
        Они автоматически добавятся в ваш стартап при запуске сервера!

        УДАЛЯТЬ НЕЛЬЗЯ -> "-jar" --- Он нужен для определения названия вашего ядра
        УДАЛЯТЬ НЕЛЬЗЯ -> ^^^^^^
        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        ЗАМЕНИТЕ -> "server.jar" <- ЗАМЕНИТЕ
        ЗАМЕНИТЕ -> ^^^^^^^^^^^^ <- ЗАМЕНИТЕ
        На название вашего файла сервера!
        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        (Можно даже указать путь к вашему ядру. В таком случае сервер запустится в нужной вам директории)
        (Например "/servers/paper/server.jar")

        (Название вашего ядра (!)ОБЯЗАТЕЛЬНО(!) должно идти после "-jar")
        ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::