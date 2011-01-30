package ACM.NWERC.y2002;
import java.util.*;

/* ACM NWERC 2002
 * Problem G: Huffman Trees
 * Type: Brute force
 * Solution: Go through adding the next point to the tree you are building up, with some smart pruning. This version
 * is about 5x too slow to pass all of the tests. 
 */

public class G
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        long st2 = System.currentTimeMillis();
        for(int zz =1; zz<=T;zz++)
        {
            long st = System.currentTimeMillis();
            N = sc.nextInt();
            D = sc.nextInt();
            s = sc.next();
            ans = null;
            recur(0,new Trie(D),new String[N],0);

            /*for(int i = 0; i < N;i++)
            {
                System.out.println(i+"->"+ans[i]);
            }*/
            long en = System.currentTimeMillis();
            System.out.println(zz+": "+(en-st)+" "+(en-st2));
        }
    }
    static String[] ans;
    static String s;
    static int N,D;
    public static void recur(int at, Trie t, String[] map, int idx)
    {
        if(t.leaves() > map.length-idx)
            return;
        if(t.chars() > s.length()-at)
            return;
        if(at == s.length() && idx == map.length && t.full())
        {
            ans = new String[map.length];
            for(int i = 0; i < ans.length;i++)
                ans[i] = map[i];
                return;
        }
        if(ans != null)
            return;
        if(idx == map.length)
            return;

        for(int i = at+1; i <= s.length() && i-at <= 20;i++)
        {
            String str = s.substring(at,i);
            if(t.legal(str))
            {
                t.add(str);
                map[idx] = str;
                recur(i,t,map,idx+1);
                map[idx] = "";
                if(ans != null)
                    return;
                t.remove(str);
            }
        }
    }
    
    private static class Trie
    {
        int N;
        TrieNode root;
        public Trie(int z)
        {
            N = z;
            root = new TrieNode(N);
        }
        public void add(String s)
        {
            add(root,s,0);
        }
        public void add(TrieNode node, String s, int pos)
        {
            int index = s.charAt(pos)-'0';
            node.count++;
            if(node.children[index] == null)
            {
                node.children[index] = new TrieNode(N);
            }
            if(pos == s.length()-1)
            {
                node.children[index].end = true;
                node.children[index].count++;
            }else{
                add(node.children[index],s,pos+1);
            }
        }

        public boolean full()
        {
            return full(root);
        }

        public boolean full(TrieNode node)
        {
            if (node == null) return false;
            if (node.end) return true;
            boolean valid = true;
            for (int i = 0; i < N; i++)
            {
                valid = valid && full(node.children[i]);
            }

            return valid;
        }
        public int leaves()
        {
            return leaves(root);
        }
        public int leaves(TrieNode n)
        {
            if(n == null)
                return 1;
            if(n.end) return 0;
            if(!hasLeaf(n))
                return 1;
            int ans = 0;
            for(int i = 0; i < N;i++)
                ans += leaves(n.children[i]);
            return ans;
        }
        public int chars()
        {
            return chars(root,0);
        }
        public int chars(TrieNode n,int depth)
        {
            if(n == null)
                return depth;
            if(n.end)
                return 0;
            if(!hasLeaf(n))
                return depth;
            int ans = 0;
            for(int i = 0; i < N;i++)
                ans += chars(n.children[i],depth+1);
            return ans;
        }
        public boolean hasLeaf(TrieNode n)
        {
            if(n == null)
                return false;
            if(n.end) 
                return true;
            boolean ans = false;
            for(int i = 0; i < N;i++)
                ans |= hasLeaf(n.children[i]);
            return ans;
        }

        public void remove(String s)
        {
            remove (root, s, 0);
        }

        public void remove(TrieNode node, String s, int pos)
        {
            int index = s.charAt(pos) - '0';
            node.count--;
            if (s.length() - 1 == pos)
            {
                node.children[index].end = false;
                node.children[index].count--;
            }
            else remove(node.children[index], s, pos + 1);
        }

        public boolean legal(String s)
        {
            return !legal(root,s,0);
        }
        public boolean legal(TrieNode n, String s, int pos)
        {
            if(n == null)
                return false;
            if(n.count == 0)
                return false;
            if(n.end)
                return true;
            if(s.length()== pos) return n.count > 0;
            int index = s.charAt(pos)-'0';
            return legal(n.children[index],s,pos+1);
        }
    }
    private static class TrieNode
    {
        TrieNode[] children;
        boolean end = false;
        int count = 0;
        public TrieNode(int N)
        {
            children = new TrieNode[N];
        }
    }
}
