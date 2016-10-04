from collections import defaultdict
words = ['abc','bca','cde']
dic = defaultdict(list)
for word in words:
    sorted_word = sorted( word )
    sorted_word = ''.join( sorted_word )
    if sorted_word in dic:
        key = dic.get( sorted_word )
        key.append( word )
    else:
        data = []
        data.append( word )
        new = { sorted_word : data }
        dic.update( new )

return_value = []
for element in dic:
    return_value.append( dic.get( element ) )
print( return_value )