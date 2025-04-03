package io;

public enum PartitionUnit {   
    KB {
        @Override
        public long getBytes(int size) {
            return (long) size << 10;
        }

        @Override
        public long getBytes(long size) {
            return size << 10;
        }
    }, 
    MB {
        @Override
        public long getBytes(int size) {
            return (long) size << 20;
        }

        @Override
        public long getBytes(long size) {
            return size << 20;
        }
    }, 
    GB {
        @Override
        public long getBytes(int size) {
            return (long) size << 30;
        }

        @Override
        public long getBytes(long size) {
            return size << 30;
        }
    };

    public abstract long getBytes(int size);
    public abstract long getBytes(long size);
}
