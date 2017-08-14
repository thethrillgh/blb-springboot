import sys
import csv

def usage():
    print 'Table name not recognized'


def main(tname, dfile):

    fname = '%s_table_data.sql' % tname
    write_to = open(fname, 'w')

    read_from = open(dfile, 'r')

    flag = True

    for line in read_from:
        if flag:
            write_to.write('INSERT INTO %s('% tname+line.strip()+')\nVALUES\n')
            flag = False
        else:
            write_to.write('('+line.strip()+'),\n')

    read_from.close()
    write_to.close()
    print 'done'

if __name__ == '__main__':

    table_name = sys.argv[1]
    data_file = sys.argv[2]

    print 'looking in table %s' % table_name

    tables = ['bond', 'useraccount', 'bankaccount', 'bondorder', 'address']

    if table_name not in tables or data_file is '':
        usage()
        sys.exit(0)
    else:
        main(table_name, data_file)