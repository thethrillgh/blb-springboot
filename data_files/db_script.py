import sys
import csv
import time
import datetime

def usage():
    print 'Table name not recognized'


def main(tname, dfile, num):

    ctr = 1
    fname = 'new_history_files/%s_table_data.sql' % (tname, int(num))
    write_to = open(fname, 'w')

    read_from = open(dfile, 'r')

    flag = True
    for line in read_from:
        if flag:
            write_to.write('INSERT INTO %s('% tname+line.strip()+')\nVALUES\n')
            flag = False
        else:
            write_to.write('('+line.strip() + '),\n')
            ctr+=1

    read_from.close()
    write_to.close()
    print 'done'

if __name__ == '__main__':

    table_name = sys.argv[1]
    data_file = sys.argv[2]
    num_file = sys.argv[3]

    print "WARNING: changed for bondhistory scripts"
    print 'looking in table %s' % table_name

    tables = ['bond', 'useraccount', 'bankaccount', 'bondorder', 'bondhistory']

    if table_name not in tables or data_file is '':
        usage()
        sys.exit(0)
    else:
        main(table_name, data_file, num_file)