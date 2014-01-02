<?php

$template = file_get_contents(__DIR__  . '/' . basename(__FILE__, '.php') . '.html');

echo $template;
