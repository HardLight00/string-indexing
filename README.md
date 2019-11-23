# String indexer
The app should group words from input string by first letter 
and sort them in lexicographical order. All groups that contains 
less than 2 words should be hidden. In every group words should be 
sorted by length in decreasing order. If two words have the same length
compare them lexicographical.
```
Example: 
"care apple car break bread boost" -> [b=[boost, bread, break], c=[care, car]]
```

## Class Indexer
Got two values - <code>min counts</code> of words with the same first letter 
and <code>delimiter</code> - string that separates words in sentence


Indexer has method <code>makeIndexing</code> that return needed result as a string