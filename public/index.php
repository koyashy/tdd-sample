<?php
/**
 * index.php
 *
 * @category App
 * @package  Pass.auone.jp
 * @author   Akira Koyasu <koyasu@mediba.jp>
 * @license  mediba inc. http://www.mediba.jp
 * @link     none
 */

$template = file_get_contents(__DIR__  . '/' . basename(__FILE__, 'php') . 'html');

echo $template;