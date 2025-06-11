from setuptools import setup, find_packages

setup(
    name='afe',
    version='0.0.3',
    author='Sincos',
    author_email='optik@koolkdzonly.club',
    description='A comprehensive collection of common algorithms and data structures implemented in Python',
    long_description=open('README.md').read(),
    long_description_content_type='text/markdown',
    url='https://github.com/MihaiStreames/AlgorithmsForEveryone',
    packages=find_packages(),
    classifiers=[
        'Programming Language :: Python :: 3',
        'License :: OSI Approved :: MIT License',
        'Operating System :: OS Independent',
    ],
    python_requires='>=3.6',
)
