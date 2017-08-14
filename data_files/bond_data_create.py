import sys
import random
import string


def gen_cusip():
    a = ''.join(random.choice(string.digits) for i in range(6))
    b = ''.join(random.choice(string.ascii_uppercase) for i in range(2))
    c = ''.join(random.choice(string.digits) for i in range(2))
    return str(a)+str(b)+str(c)

def gen_rating():
    #22 ratings
    ratings = ['AAA','AA+','AA','AA-','A+','A','A-','BBB+','BBB','BBB-','BB+','BB','BB-','B+','B','B-','CCC+','CCC','CCC-','CC','C','D']
    idx = random.randint(0,22)
    return ratings[idx]



def main(num_rows):
    for i in range(10):
        print gen_cusip()

if __name__ == '__main__':

    num_rows = sys.argv[1]

    main(num_rows)