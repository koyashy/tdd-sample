<?php
/**
 * Created by PhpStorm.
 * User: koyasu
 * Date: 2014/01/02
 * Time: 18:24
 */

class View
{
    private $_templateFile;

    function __construct($file)
    {
        $this->_templateFile = dirname($file) . '/' . basename($file, '.php') . '.html';
    }

    function render($vars = array())
    {
        $template = file_get_contents($this->_templateFile);
        foreach ($vars as $name => $val) {
            $template = str_replace('${' . $name . '}', $val, $template);
        }
        echo $template;
    }
} 