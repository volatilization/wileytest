# wileytest
Storage - container for Stored objects.
CachedStorage - proxy for Storage. It caches the result of the method #get.
When the cache size is full, one of the items in Cache will be replaced by a new one from Storage.
The element to be replaced is selected according to the LFU/LRU algorithm.

You can select the desired algorithm by specifying it in the CachedStorage constructor.
Also, in this constructor, you need to specify the Storage to be wrapped and the size of the cache.
Example is in the Main class;
