package file;

/**
 * A文件每行3个字段A1，A2，A3，B文件每行两个字段B1，B2，A中某行A2与B中某行B2相等，输出A文件该行的A1，A2，A3和B文件的B1字段到新文件
 * 思路：bufferedReader遍历两个文件的每行，先A截取A2做key，该行内容做value，再遍历B，截取B2判断map中是否存在key,命中则输出
 */
public class ABFileTest {
}
