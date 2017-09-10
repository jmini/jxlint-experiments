In a text file (ending with `.txt`) there should be no multiple new line at the end of the document.
One single new line is OK, but not multiple

This example is OK:

    ----
    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    Mauris faucibus massa at erat feugiat cursus.
    ----

This one is also OK:

    ----
    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    Mauris faucibus massa at erat feugiat cursus.
    \ No newline at end of file
    ----

This one is not OK:

    ----
    Lorem ipsum dolor sit amet, consectetur adipiscing elit.
    Mauris faucibus massa at erat feugiat cursus.
    
    ----

Lines containing only white spaces are considered as empty.
